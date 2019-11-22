package ReactorSimulator;
/*
 * 
 */

import java.util.*;
public abstract class TubularReactor implements ODEEquation{
  
  // Relevant constants
  public static final double R = 8.3145; // Ideal gas constant
  public static final double ERROR_TOLERANCE=0.001; //epsilon
  public static final int MAX_ITERATIONS = 10000;
  public static final double STEP_SIZE = 0.1;
  
  private ReactionSystem reactionSystem;
  private double initialPressure;
  private double initialTemperature;
  private double initialTotalVolume;
  private double initialVolumetricFlowRate;
  
  private int odeSolverMethod=2; // 1- Euler, 2-RK45 with adaptive step-sizing 
  
  protected int g_index=-1;
  protected double [] g_concentrations;
  public ArrayList<ArrayList<Double>> g_data;
  public double [] g_finalFlowRates;
  
  public TubularReactor (ReactionSystem reactionSystem, double initialTotalVolume, double initialVolumetricFlowRate){
    this.reactionSystem = reactionSystem;
    this.initialTotalVolume = initialTotalVolume;
    this.initialVolumetricFlowRate = initialVolumetricFlowRate;
  }
  
  public TubularReactor (ReactionSystem reactionSystem, double initialPressure, double initialTemperature, double initialTotalVolume, double initialVolumetricFlowRate){
    this.reactionSystem = reactionSystem;
    this.initialPressure = initialPressure;
    this.initialTemperature = initialTemperature;
    this.initialTotalVolume = initialTotalVolume;
    this.initialVolumetricFlowRate = initialVolumetricFlowRate;
  }    
  
  public TubularReactor (TubularReactor source){
    this.reactionSystem = source.reactionSystem;
    this.initialPressure = source.initialPressure;
    this.initialTemperature = source.initialTemperature;
    this.initialTotalVolume = source.initialTotalVolume;
    this.initialVolumetricFlowRate = source.initialVolumetricFlowRate;
  }
  
  //Accessors  
  public ReactionSystem getReactionSystem(){
    return this.reactionSystem;
  }
  public double getInitialPressure(){
    return this.initialPressure;
  }
  public double getInitialTemperature(){
    return this.initialTemperature;
  }
  public double getInitialTotalVolume(){
    return this.initialTotalVolume;
  }
  public double getInitialVolumetricFlowRate(){
    return this.initialVolumetricFlowRate;
  }
  public int getODESolverMethod(){
    return this.odeSolverMethod;
  }// end of getODESolverMethod method 
  
  public ArrayList get_g_data(){
    return g_data;
  }
  
  //Mutators
  public void setReactionSystem(ReactionSystem reactionSystem){
    this.reactionSystem= reactionSystem;
  }
  public void setInitialPressure(double initialPressure){
    this.initialPressure=initialPressure;
  }
  public void setInitialTemperature(double initialTemperature){
    this.initialTemperature= initialTemperature;
  }
  public void setInitialTotalVolume(double initialTotalVolume){
    this.initialTotalVolume =initialTotalVolume;
  }
  public void setInitialVolumetricFlowRate(double initialVolumetricFlowRate){
    this.initialVolumetricFlowRate = initialVolumetricFlowRate;
  }
  
  public void setODESolverMethod(int odeSolverMethod){
    this.odeSolverMethod = odeSolverMethod;
  }// end of setODESolverMethod method
  
  
  //Methods
  public abstract TubularReactor clone ();
  
  public double findInitialTotalFlowRate(){
    double totalFlowRate=0.0;  
    for (int i=0; i<this.reactionSystem.getSpecies().length; i++){
      totalFlowRate += this.reactionSystem.getSpecies()[i].getInitialFlowRate();     
    }
    return totalFlowRate;
  }
  
  public double findInitialTotalConcentration(){
    return findInitialTotalFlowRate()/this.initialVolumetricFlowRate;
  }  
  
  public void solveSystem(){
    
    // Local variables
    double [] currentFlowRates = new double [this.getReactionSystem().getSpecies().length]; 
    double [] nextFlowRates = new double [this.getReactionSystem().getSpecies().length]; 
    double currentVolume=0.0;
    double nextVolume=0.0;
    double totalFlowRate=0.0; 
    double initialTotalConcentration = findInitialTotalConcentration();
    g_concentrations = new double [this.getReactionSystem().getSpecies().length];
    double [] rateLawSpecies = new double [this.getReactionSystem().getSpecies().length];
    double error=0;
    int index=0;
    int maximize=-1;
    int minimize=-1;
    double conversion=0.0;
    int counter =0;
    //double [][] selectivity = new double [this.reactionSystem.getReactions().length][this.reactionSystem.getReactions().getProducts().length]; 
    g_data = new ArrayList<ArrayList<Double>>();
    ArrayList<Double>iteration = new ArrayList<Double>();
        
    /*
     * "V (m3)","Fa (mol/s)","Fb (mol/s)", "Fc (mol/s)", "Fd(mol/s)", "Fe(mol/s)","Ft (mol/s)","Pa (mol/m3)","Pb (mol/m3)","Pc (mol/m3)","Pd (mol/m3)", "Pe (mol/m3)", 
     "Ca (mol/m3)","Cb (mol/m3)","Cc (mol/m3)","Cd (mol/m3)", "Ce (mol/m3)", "r1basis (mol/kg.s)","r2basis (mol/kg.s)","r3basis (mol/kg.s)","ra (mol/kg.s)","rb (mol/kg.s)","rc (mol/kg.s)", "rd (mol/kg.s)","re (mol/kg.s)",
     "Vi+1 (m3)", "error (%)", "selectivity"  
     */
    
    // Preliminary calculations to determine reactant and product coefficient ratios and rate constant in terms of limiting reactant
    for (int i=0; i<this.reactionSystem.getReactions().length; i++){
      this.reactionSystem.getReactions()[i].findReactantsCoefficientRatios();
      this.reactionSystem.getReactions()[i].findProductsCoefficientRatios();
      this.reactionSystem.getReactions()[i].convertRateConstantToBasis(); 
    }
    // Identifying target flow rate to mazimize and/or minimize
    for (int i =0;i<this.getReactionSystem().getSpecies().length;i++){
      if (this.reactionSystem.getToMaximize().equals(this.getReactionSystem().getSpecies()[i])) maximize=i;
      if (this.reactionSystem.getToMinimize().equals(this.getReactionSystem().getSpecies()[i])) minimize=i;
    }    
    // Determining initial flow rates
    for (int i =0;i<this.getReactionSystem().getSpecies().length;i++){
      currentFlowRates [i] = this.getReactionSystem().getSpecies()[i].getInitialFlowRate();
    }
    
    while (counter<=TubularReactor.MAX_ITERATIONS && error<=TubularReactor.ERROR_TOLERANCE){
     
      iteration.add(currentVolume);
      
      int i=0;
      for (i=0;i<this.getReactionSystem().getSpecies().length;i++){
        iteration.add(currentFlowRates[i]); 
        totalFlowRate+=currentFlowRates[i]; 
      }
      iteration.add(totalFlowRate);
      if(this.getReactionSystem().getPhase()==1)//liquid
      {
        for (i=0; i<this.getReactionSystem().getSpecies().length;i++){
          g_concentrations[i] = currentFlowRates[i]/this.getInitialVolumetricFlowRate();
          iteration.add(g_concentrations[i]);
        }
      }
      else if (this.getReactionSystem().getPhase()==2)// gas
      {
        for (i=0; i<this.getReactionSystem().getSpecies().length;i++){  
          g_concentrations[i] = initialTotalConcentration*currentFlowRates[i]/totalFlowRate;
          iteration.add(g_concentrations[i]);
          iteration.add(initialTotalConcentration);
        }
      }
      for (i=0;i<this.getReactionSystem().getSpecies().length;i++){
        rateLawSpecies [i] = this.getReactionSystem().sumRateLawsPerSpecie(this.reactionSystem.getSpecies(), this.getReactionSystem().getSpecies()[i], g_concentrations); 
        iteration.add(rateLawSpecies [i]);
      }
      
      nextVolume = currentVolume+TubularReactor.STEP_SIZE;
      
      if (this.getODESolverMethod()==1){
        EulersMethod eulersMethod = new EulersMethod (this, TubularReactor.STEP_SIZE);
        for (g_index=0; g_index<this.getReactionSystem().getSpecies().length;g_index++){
          nextFlowRates[g_index]= eulersMethod.solveODE(currentVolume, currentFlowRates[g_index], nextVolume);
          iteration.add(nextFlowRates[g_index]);
        } 
      }
      else if (this.getODESolverMethod()==2){
        RungeKutta45 rungeKutta45 = new RungeKutta45 (this, TubularReactor.STEP_SIZE);
        for (g_index=0; g_index<this.getReactionSystem().getSpecies().length;g_index++){
          nextFlowRates[g_index]= rungeKutta45.solveODE(currentVolume, currentFlowRates[g_index], nextVolume);
          iteration.add(nextFlowRates[g_index]);
        } 
      }
      
      if (nextFlowRates[maximize]-currentFlowRates[maximize]<=0.001) break;
      if (nextFlowRates[minimize]-currentFlowRates[minimize]>=0.001) break;
      if (this.getInitialTotalVolume()!=-1.0 && currentVolume >=this.getInitialTotalVolume()) break;
      
      iteration.add(nextVolume);
      
      error = nextFlowRates[0]-currentFlowRates[0];
      iteration.add(error);
      
      for (i=0; i<this.getReactionSystem().getSpecies().length;i++){
        currentFlowRates[i]=nextFlowRates[i];
      }
      currentVolume = nextVolume;
      totalFlowRate =0;
      conversion = (this.getReactionSystem().getSpecies()[0].getInitialFlowRate()-currentFlowRates[0])/this.getReactionSystem().getSpecies()[0].getInitialFlowRate();
      iteration.add(conversion);
      counter++;
      g_data.add(iteration);
  }
    System.out.println("Current Volume: "+currentVolume);
    System.out.println("Conversion: "+conversion);
    g_finalFlowRates = new double [this.reactionSystem.getSpecies().length];

    for (int i=0; i<this.reactionSystem.getSpecies().length;i++){
      g_finalFlowRates[i]= currentFlowRates[i];
      System.out.println("Final flow rate: "+g_finalFlowRates[i]);
    }
    }// end of solveSystem method

  public abstract double equation (double x, double y);
  
  public String toString (){
    String string ="";
    string = this.reactionSystem.toString();
    if (this.initialPressure!=-1)
      string +="\nThe initial pressure is : "+ this.initialPressure;
    if (this.initialTemperature!=-1)
      string +="\nThe initial pressure is : "+ this.initialTemperature;
    if (this.initialTotalVolume!=-1)
      string +="\nThe total volume is : "+ this.initialTotalVolume;
    if (this.initialVolumetricFlowRate!=-1)  
      string +="\nThe initial volumetric flow rate is : "+ this.initialVolumetricFlowRate;
    return string; 
  }   
  
  }// end of TubularReactor class
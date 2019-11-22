package ReactorSimulator;
/*
 * 
 */
public class Species {
  
  //Instance variables
  private char name;
  private double initialFlowRate=-1.0;
  private double initialConcentration=-1;
  private double rateConstant;
  private double maxRateChangeFlow;
  private double transportCoefficient; 
  private double shellSideConcentration;
  
  //Constructor
  public Species (char name, double initialFlowRate,double maxRateChangeFlow) {
    this.name = name; 
    this.initialFlowRate = initialFlowRate;
    this.maxRateChangeFlow = maxRateChangeFlow;
  }
  public Species (char name, double initialFlowRate, double rateConstant, double maxRateChangeFlow) {
    this.name = name; 
    this.initialFlowRate = initialFlowRate;
    this.rateConstant= rateConstant;
    this.maxRateChangeFlow = maxRateChangeFlow;
  }
  
  public Species (char name, double initialFlowRate, double initialConcentration, double rateConstant, double maxRateChangeFlow,
                  double transportCoefficient, double shellSideConcentration) {
    this.name = name; 
    this.initialFlowRate = initialFlowRate;
    this.initialConcentration = initialConcentration; 
    this.rateConstant= rateConstant;
    this.maxRateChangeFlow = maxRateChangeFlow;
    this.transportCoefficient = transportCoefficient;
    this.shellSideConcentration = shellSideConcentration;
  }
  
  //Copy Constructor
  public Species (Species source){
    this.name = source.name;
    this.initialFlowRate = source.initialFlowRate;
    this.initialConcentration = source.initialConcentration;
    this.rateConstant = source.rateConstant;
    this.maxRateChangeFlow = source.maxRateChangeFlow;
    this.transportCoefficient = source.transportCoefficient;
    this.shellSideConcentration = source.shellSideConcentration;
  }
  
  //Accessors
  public char getName(){
    return this.name;
  }
  public double getInitialFlowRate() {
    return this.initialFlowRate;
  }
  public double getInitialConcentration(){
    return this.initialConcentration;
  }
  public double getRateConstant (){
    return this.rateConstant;
  } 
  public double getMaxRateChangeFlow(){
    return this.maxRateChangeFlow;
  }
  public double getTransportCoefficient (){
    return this.transportCoefficient;
  }
  public double getShellSideConcentration(){
    return this.shellSideConcentration;
  }
  //Mutators
  public void setName (char name){
    this.name = name;
  }
  public void setInitialFlowRate (double initialFlowRate){
    this.initialFlowRate = initialFlowRate;
  } 
  public void setInitialConcentration (double initialConcentration){
    this.initialConcentration= initialConcentration;
  }
  public void setRateConstant (double rateConstant){
    this.rateConstant = rateConstant;
  }
  public void setMaxRateChangeFLow(double maxRateChangeFlow){
    this.maxRateChangeFlow = maxRateChangeFlow;
  }
  public void setTransportCoefficient (double transportCoefficient){
    this.transportCoefficient=transportCoefficient;
  }
  public void setShellSideConcentration(double shellSideConcentration){
    this.shellSideConcentration= shellSideConcentration;
  }
  // Methods
  
  public double findInitialRate (){
    if (this.initialFlowRate !=-1.0)
      return this.initialFlowRate;
    else return initialConcentration;     
  }
  /*   
   public boolean equals (Species specie){
   if (specie!=null && specie instanceof Species && specie.getName() == this.name)
   return true;
   else return false;
   }
   */
  public boolean equals(Object source){
    if (source == null)
      return false;
    else if (source.getClass() != this.getClass())
      return false;
    else {
      Species object = (Species)source;
      return (this.name == object.name);
    }
  }
  
  public String toString (){
    String string="";
    string = "Species name: "+this.name+"\nInitial flow rate: "+this.initialFlowRate+"\n";
    if (initialConcentration!=-1){
      string +="Initial Concentration: "+this.initialConcentration+"\n";
    }
    if (rateConstant !=-1){
      string +="Rate Constant: "+this.rateConstant+"\n";
    }
    if (maxRateChangeFlow !=-1){
      string +="Maximum rate of change for flow: "+this.maxRateChangeFlow+"\n";
    }       
    if (transportCoefficient!=-1){
      string +="Transport Coefficient: "+this.transportCoefficient+"\n";
    }   
    if (shellSideConcentration!=-1){
      string +="Shell Side Concentration: "+this.shellSideConcentration+"\n";
    } 
    return string;
  }
  
}// end of Species class
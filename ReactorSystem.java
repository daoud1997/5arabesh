package ReactorSimulator;
/*
 * 
 */

import java.util.*;

public class ReactorSystem {
  
  //Instance variables
  private int configuration; 
  private int numberReactors;
  private TubularReactor [] reactors; 
  
  //Constructor
  
  public ReactorSystem(){}
  
  public ReactorSystem (TubularReactor[] reactors){
    this.reactors = new TubularReactor[numberReactors];  
    for (int i=0; i<numberReactors; i++){
      this.reactors [i] = reactors [i]; 
    }
  }
  
  public ReactorSystem (int configuration, int numberReactors, TubularReactor [] reactors){
    this.configuration = configuration;
    this.numberReactors = numberReactors;
    this.reactors = new TubularReactor[numberReactors];  
    for (int i=0; i<numberReactors; i++){
      this.reactors [i] = reactors [i]; 
    }
  }
  
  //Copy Constructor
  public ReactorSystem (ReactorSystem source){
    this.configuration = source.configuration;
    this.numberReactors = source.numberReactors;
    this.reactors = new TubularReactor[source.numberReactors];  
    for (int i=0; i<source.numberReactors; i++){
      this.reactors [i] = source.reactors [i]; 
    }
  }
  
  //Accessors
  public int getConfiguration(){
    return this.configuration;
  }  
  public int  getNumberReactors(){
    return this.numberReactors;
  }     
  public TubularReactor [] getReactors(){
    return this.reactors;
  }
  
  //Mutators
  public void setConfiguration(int configuration){
    this.configuration = configuration;
  }
  public void setNumberReactors (int numberReactors){
    this.numberReactors = numberReactors;
  }
  public void setReactors (TubularReactor [] reactors){
    this.reactors = new TubularReactor [reactors.length];
    for (int i=0; i<reactors.length;i++){
      this.reactors[i]= reactors[i];
    }
  }
  
  
  public ArrayList getData (){
    ArrayList<ArrayList<ArrayList<Double>>> data = new  ArrayList<ArrayList<ArrayList<Double>>>();
    for (int i=0;i<this.getReactors().length;i++){
      data.add(this.getReactors()[i].get_g_data());
    }  
  return data;
  }
  
  /* public boolean equals(Object source){
   if (source == null)
   return false;
   else if (source.getClass() != this.getClass())
   else {
   Species object = (Species)source;
   return (this.name == object.name);
   private Species [] reactants;
   private Species [] species;
   private Reaction [] reactions;
   private double conversion;
   private double productionRate;
   private double totalInitialConcentration; 
   private int phase; // 1=liquid 2=gaz 3=solid 4=mixture 
   } 
   }*/
  
  //Methods
  /*  
   public String toString (){
   String string="";
   
   string = "Reaction System: \nThis reaction system contains "+this.species.length+" species";
   for (int i=0; i<this.species.length;i++){
   string += "\n"+this.species[i];
   }    
   
   string += "\nThis reaction system contains "+this.reactions.length+" reaction(s)";
   
   for (int i=0; i<this.reactions.length;i++){
   string += "\nReaction # "+(i+1)+"\n"+this.reactions[i];
   }  
   if (this.conversion !=-1)
   string += "\nThe conversion is: "+this.conversion;
   if (this.productionRate !=-1)
   string += "\nThe production rate is: "+this.productionRate;
   if (this.totalInitialConcentration !=-1)
   string += "\nThe total initial concentration is: "+this.totalInitialConcentration;
   if(this.phase ==1)
   string += "\nThe phase is liquid";
   if (this.phase ==2)
   string += "\nThe phase is gas";
   if (this.phase ==3)
   string += "\nThe phase is solid";  
   if (this.phase ==4)
   string += "\nThe phase is mixed";    
   return string; 
   }
   */
}// end of ReactorSystem class
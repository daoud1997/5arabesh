package ReactorSimulator;
/*
 * 
 */

import java.util.*;

public class ReactorSystem {
  
  //Instance variables
  private int configuration; //TODO: NOT SURE WhAT u mean by configuration but ill assume it cant be zero or less Change it if its not the case
  private int numberReactors; //Assuming the number of TubularReactor = numberReactors and cant be zero or less
  private TubularReactor [] reactors; 
  
  //Constructor
  
  public ReactorSystem(){}
  
  public ReactorSystem (TubularReactor[] reactors){
	  if(reactors == null)
		  throw new CouldNotConstructObjectException("Could not initiate ReactorSystem");
    this.reactors = new TubularReactor[numberReactors];  
    for (int i=0; i<numberReactors; i++){
    	if(reactors[i]==null)
    		throw new CouldNotConstructObjectException("Could not initiate ReactorSystem");
      this.reactors [i] = reactors[i].clone(); 	//SINCE TUBULAR REACTOR IS ABSTRACT 
    }
  }
  
  public ReactorSystem (int configuration, int numberReactors, TubularReactor [] reactors){
	  if(reactors == null || configuration <= 0 || numberReactors <=0)
		  throw new CouldNotConstructObjectException("Could not initiate ReactorSystem");
    this.reactors = new TubularReactor[numberReactors];  
    for (int i=0; i<numberReactors; i++){
    	if(reactors[i]==null)
    		throw new CouldNotConstructObjectException("Could not initiate ReactorSystem");
      this.reactors [i] = reactors[i].clone(); 	//SINCE TUBULAR REACTOR IS ABSTRACT 
    }
    this.configuration = configuration;
    this.numberReactors = numberReactors;
  }
  
  //Copy Constructor
  public ReactorSystem (ReactorSystem source){
	  if(source == null)
		  throw new CouldNotConstructObjectException("Could not initiate copy of ReactorSystem");
    this.configuration = source.configuration;
    this.numberReactors = source.numberReactors;
    this.reactors = new TubularReactor[source.numberReactors];  
    for (int i=0; i<source.numberReactors; i++){
      this.reactors [i] = source.reactors [i].clone(); 
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
	  TubularReactor [] copy = new TubularReactor[this.reactors.length];
	  for(int i =0; i<copy.length; i++) {
		  copy[i] = this.reactors[i].clone(); //SINCE ABSTRACT
	  }
    return copy;
  }
  
  
  
  //Mutators
  public boolean setConfiguration(int configuration){
	  if(configuration <= 0)
		  return false;
    this.configuration = configuration;
    return true;
  }
  public boolean setNumberReactors (int numberReactors){
	  if(numberReactors<= 0)
		  return false;
    this.numberReactors = numberReactors;
    return true;
  }
  public boolean setReactors (TubularReactor [] reactors){
	  if(reactors == null|| reactors.length != this.reactors.length)
		  return false;
    this.reactors = new TubularReactor [reactors.length];
    for (int i=0; i<reactors.length;i++){
    	if(reactors[i]==null)
    		return false;
      this.reactors[i]= reactors[i].clone();
    }
    return true;
  }
  
  
  public ArrayList getData (){
    ArrayList<ArrayList<ArrayList<Double>>> data = new  ArrayList<ArrayList<ArrayList<Double>>>();
    for (int i=0;i<this.getReactors().length;i++){
      data.add(this.getReactors()[i].get_g_data());
    }  
  return data;
  }
  
  //Clone Method
  public ReactorSystem clone() {
	  return new ReactorSystem(this);
  }
  

  //Equals
  public boolean equals(Object source){
	  if (source == null)
	      return false;
else if (source.getClass() != this.getClass())
	      return false;
else {
	      ReactorSystem object = (ReactorSystem)source;
	      if((object.reactors.length != this.reactors.length))
	    	  return false;
	      boolean other = true;
	      for(int i =0; i<object.reactors.length;i++) {
	    	  if(!(this.reactors[i].equals(object.reactors[i])))
	    		  other = false;
	      }
	      
	      if(object.configuration != this.configuration)
	    	  other = false;
	      if(object.numberReactors != this.numberReactors)
	    	  other = false;
	     return other;
	    }
   }
  
  //To String 
   public String toString (){
   String string="";
   
   string = "Reactor System: \nThis reaction system contains "+this.reactors.length+" reactor(s)";
   for (int i=0; i<this.reactors.length;i++){
   string += "\n"+this.reactors[i];
   }    
   
   string += "\nThis reactor system also contains "+this.configuration+" configuration(s)" + " and number of reactors is "+this.numberReactors;
      
   return string; 
   }
   
}// end of ReactorSystem class
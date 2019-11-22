package ReactorSimulator;
/*
 * 
 */
public class PlugFlowReactor extends TubularReactor{
  
  //Constructor
  public PlugFlowReactor (ReactionSystem reactionSystem, double initialTotalVolume, double initialVolumetricFlowRate){
    super(reactionSystem, initialTotalVolume,initialVolumetricFlowRate);
  }// end of Constructor
  
  public PlugFlowReactor (ReactionSystem reactionSystem, double initialPressure, double initialTemperature, double initialTotalVolume, double initialVolumetricFlowRate){
    super(reactionSystem, initialPressure,initialTemperature,initialTotalVolume,initialVolumetricFlowRate);
  }// end of Constructor
  
  //Copy Constructor
  public PlugFlowReactor(PlugFlowReactor source){
    super(source.getReactionSystem(), source.getInitialPressure(), source.getInitialTemperature(),source.getInitialTotalVolume(),source.getInitialVolumetricFlowRate());
  }// end of Copy Constructor
  
  //Methods 
  public PlugFlowReactor clone(){
    return new PlugFlowReactor(this);
  }// end of clone method
    
  public double equation(double x, double y){
    return (this.getReactionSystem().sumRateLawsPerSpecie(this.getReactionSystem().getSpecies(),this.getReactionSystem().getSpecies()[g_index], g_concentrations));
    
  }        
  //SINCE AM ASSUMING THERE WILL BE NO ADDITIONAL INSTANCE VARIABLES OR METHODS WE WILL JUST CHECK THE SUPER EQUALS
  public boolean equals(Object source){
    if (source == null)
      return false;
    else if (source.getClass() != this.getClass())
      return false;
    else {
      PlugFlowReactor object = (PlugFlowReactor)source;
      if(!super.equals(object))
    	  return false;
      return true;
    }
  }// end of equals Method
  
  public String toString (){
    return  "This is an isobaric and isothermal Plug Flow Reactor containing the following reaction system: \n"+super.toString();     
  }// end of toString method
  
}//end of PlugFlowReactor class
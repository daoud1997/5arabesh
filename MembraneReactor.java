package ReactorSimulator;
/*
 * 
 */

public class MembraneReactor extends TubularReactor{
  
  Species speciesTransported; // 
  boolean directionIn; // in = true, out = false
  boolean diffusive; //diffusive transport = true , convective transport = false

  
  //Constructor
  public MembraneReactor (ReactionSystem reactionSystem, double initialTotalVolume, double initialVolumetricFlowRate, Species speciesTransported, boolean directionIn, boolean diffusive){
    super(reactionSystem,initialTotalVolume,initialVolumetricFlowRate);
    this.speciesTransported = new Species (speciesTransported);
    this.directionIn = directionIn;
    this.diffusive = diffusive;
  }// end of Constructor
  
  public MembraneReactor (ReactionSystem reactionSystem, double initialPressure, double initialTemperature, double initialTotalVolume, double initialVolumetricFlowRate, Species speciesTransported, boolean directionIn, boolean diffusive){
    super(reactionSystem, initialPressure,initialTemperature,initialTotalVolume,initialVolumetricFlowRate);
    this.speciesTransported = new Species (speciesTransported);
    this.directionIn = directionIn;
    this.diffusive = diffusive;
  }// end of Constructor
  
  //Copy Constructor 
  public MembraneReactor(MembraneReactor source){
    super(source);
  }// end of Copy Constructor
  
  //Methods
  public MembraneReactor clone(){
    return new MembraneReactor (this);
  }// end of clone method
    
  public double equation (double x, double y){
    if (this.getReactionSystem().getSpecies()[g_index].equals(speciesTransported)){
      if (diffusive){
        if (directionIn){
        return (this.getReactionSystem().sumRateLawsPerSpecie(this.getReactionSystem().getSpecies(),this.getReactionSystem().getSpecies()[g_index], g_concentrations)+ this.getReactionSystem().getSpecies()[g_index].getTransportCoefficient()*g_concentrations[g_index]); 
        }
        else return (this.getReactionSystem().sumRateLawsPerSpecie(this.getReactionSystem().getSpecies(), this.getReactionSystem().getSpecies()[g_index], g_concentrations)-this.getReactionSystem().getSpecies()[g_index].getTransportCoefficient()*g_concentrations[g_index]); 
      }
      else {return -100000.0;}
    }
    else return (this.getReactionSystem().sumRateLawsPerSpecie(this.getReactionSystem().getSpecies(),this.getReactionSystem().getSpecies()[g_index], g_concentrations)); 
  }// end of equation method
  
  public boolean equals(Object source){
    if (source == null)
      return false;
    else if (source.getClass() != this.getClass())
      return false;
    else {
      MembraneReactor object = (MembraneReactor)source;
      return (this.getInitialTotalVolume() == object.getInitialTotalVolume());
    }
  }// end of equals method
  
  public String toString (){
    return  "This is an isobaric and isothermal Membrane Reactor containing the following reaction system: \n"+super.toString();     
  }// end of toString method  
  
}// end of MembraneReactor class
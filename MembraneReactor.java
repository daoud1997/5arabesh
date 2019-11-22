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
    if(speciesTransported == null)
    	throw new CouldNotConstructObjectException("Could not construct Species");
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
    if(source == null)
		  throw new CouldNotConstructObjectException("Could not initiate MembraneReactor copy constructor");
    this.speciesTransported = new Species(source.speciesTransported);
    this.diffusive = source.diffusive;
    this.directionIn = source.directionIn;
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
  
  
  
 //Setters
public boolean setSpeciesTransported(Species speciesTransported) {
	if(speciesTransported == null)
		return false;
	this.speciesTransported = new Species (speciesTransported);
	return true;
}
//void return since no conditions are required since boolean
public void setDirectionIn(boolean directionIn) {
	this.directionIn = directionIn;
}
//void return since no conditions are required since boolean
public void setDiffusive(boolean diffusive) {
	this.diffusive = diffusive;
}

//Getters
public Species getSpeciesTransported() {
	return new Species(this.speciesTransported);
}

public boolean isDirectionIn() {
	return this.directionIn;
}

public boolean isDiffusive() {
	return this.diffusive;
}


//I believe in equals u check the instance variable of this class and super.equals, Another way to do this, check other classes equals
public boolean equals(Object source){
  if (source == null)
    return false;
  else if (source.getClass() != this.getClass())
    return false;
  else {
    MembraneReactor object = (MembraneReactor)source;
    boolean isSpeciesTransported = (this.getSpeciesTransported() == object.getSpeciesTransported());
    boolean isDirection = (this.isDirectionIn() == object.isDirectionIn());
    boolean isDiffusion = (this.isDiffusive() == object.isDiffusive());
    boolean isSuper = (!super.equals(object));
    return (isSpeciesTransported && isDirection && isDiffusion && isSuper);
  }
}// end of equals method

public String toString (){
    return  "This is an isobaric and isothermal Membrane Reactor containing the following reaction system: \n"+super.toString();     
  }// end of toString method  


  
}// end of MembraneReactor class
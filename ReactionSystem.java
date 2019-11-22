package ReactorSimulator;
/*
 * 
 */
public class ReactionSystem {
  
  // Instance variables 
  private Species [] species;
  private Reaction [] reactions;
  private double desiredConversion;
  private double productionRate;
  private double totalInitialConcentration; 
  private int phase; // 1=liquid 2=gaz 3=solid 4=mixture   ASSUMING PHASE CAN NOT BE ZERO OR NEGATIVE!
  private Species toMaximize;
  private Species toMinimize;
  
  //Constructor
  public ReactionSystem (Species [] species, Reaction[] reactions, int phase){
	if(species == null || reactions == null || phase<= 0)
		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
    this.species = new Species[species.length];
    for (int i =0; i<species.length;i++){
    	if(species[i] == null)
    		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
      this.species[i]=new Species(species[i]);
    } 
    this.reactions = new Reaction [reactions.length];
    for (int i =0; i<reactions.length;i++){
    	if(reactions[i] == null)
    		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
      this.reactions[i]= new Reaction(reactions[i]);
    }
    this.phase = phase;
  }
  
  public ReactionSystem (Species [] species, Reaction[] reactions, double totalInitialConcentration, int phase, Species toMaximize, Species toMinimize){
	  if(species == null || reactions == null || toMaximize == null || toMinimize == null || phase<= 0)
			throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
	    this.species = new Species[species.length];
	    for (int i =0; i<species.length;i++){
	    	if(species[i] == null)
	    		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
	      this.species[i]=new Species(species[i]);
	    } 
	    this.reactions = new Reaction [reactions.length];
	    for (int i =0; i<reactions.length;i++){
	    	if(reactions[i] == null)
	    		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
	      this.reactions[i]= new Reaction(reactions[i]);
	    }
    this.totalInitialConcentration=totalInitialConcentration;
    this.phase = phase;
    this.toMaximize = new Species (toMaximize);
    this.toMinimize = new Species (toMinimize);
  }
  
  
  public ReactionSystem (Species [] species, Reaction[] reactions, double desiredConversion, double productionRate, double totalInitialConcentration, 
                         int phase, Species toMaximize, Species toMinimize){
	  
	  if(species == null || reactions == null || toMaximize == null || toMinimize == null || desiredConversion<0||productionRate<0 ||
			  		totalInitialConcentration<0 ||phase<= 0)
			throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
	    this.species = new Species[species.length];
	    for (int i =0; i<species.length;i++){
	    	if(species[i] == null)
	    		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
	      this.species[i]=new Species(species[i]);
	    } 
	    this.reactions = new Reaction [reactions.length];
	    for (int i =0; i<reactions.length;i++){
	    	if(reactions[i] == null)
	    		throw new CouldNotConstructObjectException("Could not initiate ReactionSystem");
	      this.reactions[i]= new Reaction(reactions[i]);
	    }
    this.desiredConversion = desiredConversion;
    this.productionRate = productionRate;
    this.totalInitialConcentration=totalInitialConcentration;
    this.phase = phase;
    this.toMaximize = new Species (toMaximize);
    this.toMinimize = new Species (toMinimize);
  }
  
  //Copy Constructor
  public ReactionSystem (ReactionSystem source){
	  if(source == null)
		  throw new CouldNotConstructObjectException("Could not initiate copy ReactionSystem"); 
    this.species = new Species[source.species.length];
    for (int i =0; i<source.species.length;i++){
      this.species[i]=source.species[i];
    }
    this.reactions = new Reaction [source.reactions.length];
    for (int i =0; i<source.reactions.length;i++){
      this.reactions[i]=source.reactions[i];
    }
    this.desiredConversion = source.desiredConversion;
    this.productionRate = source.productionRate;
    this.totalInitialConcentration=source.totalInitialConcentration;
    this.phase = source.phase;
    this.toMaximize = new Species(source.toMaximize);
    this.toMinimize = new Species(source.toMinimize);
  }
  
  //Accessors
  public Species [] getSpecies(){
	  Species [] copy = new Species[this.species.length];
	  for(int i =0; i<copy.length;i++) {
		  copy[i] = new Species(this.species[i]);
	  }
    return copy;
  }
  public Reaction[] getReactions(){
	  Reaction [] copy = new Reaction[this.reactions.length];
	  for(int i =0; i<copy.length;i++) {
		  copy[i] = new Reaction(this.reactions[i]);
	  }
    return copy;
  }  
  public double getConversion(){
    return this.desiredConversion;
  }
  public double getProductionRate(){
    return this.productionRate;
  }
  public double getTotalInitialConcentration(){
    return this.totalInitialConcentration;
  }
  public int getPhase (){
    return this.phase;
  }
  public Species getToMaximize (){
    return new Species(this.toMaximize);
  }
  public Species getToMinimize (){
    return new Species(this.toMinimize);
  }
  
  //Mutators  
  public boolean setSpecies (Species [] species){
	  if(species == null || species.length != this.species.length)
		  return false;
    this.species = new Species [species.length];
    for (int i=0; i<species.length;i++){
    	if(species[i]==null)
    		return false;
      this.species[i]=new Species(species[i]);
    }
    return true;
  }
  public boolean setReactions(Reaction [] reactions){
	  if(reactions == null || reactions.length != this.reactions.length)
		  return false;
    this.reactions = new Reaction [reactions.length];
    for (int i=0; i<reactions.length;i++){
    	if(reactions[i]==null)
    		return false;
      this.reactions[i]=new Reaction(reactions[i]);
    }
    return true;
  }  
  public boolean setConversion(double desiredConversion){
	  if(desiredConversion<0)
		  return false;
    this.desiredConversion = desiredConversion;
    return true;
  }
  public boolean setProductionRate(double productionRate){
	  if(productionRate < 0)
		  return false;
	  this.productionRate= productionRate;
	  return true;
  }
  public boolean setTotalInitialConcentration(double totalInitialConcentration){
	  if(totalInitialConcentration<0)
		  return false;
    this.totalInitialConcentration = totalInitialConcentration;
    return true;
  }
  public boolean setPhase (int phase){
	  if(phase <= 0)
		  return false;
    this.phase = phase;
    return true;
  }
  public boolean setToMaximize (Species toMaximize){
	  if(toMaximize == null)
		  return false;
   this.toMaximize= new Species (toMaximize) ;
   return true;
  }
  public boolean setToMinimize (Species toMinimize){
	  if(toMinimize == null)
		  return false;
   this.toMinimize= new Species (toMinimize) ;
   return true;
  }
  
  //Methods
  public double sumRateLawsPerSpecie(Species[] species, Species x, double[] concentrations){
    double sumRateLaws=0.0;
    for (int i=0; i<this.reactions.length;i++){
          sumRateLaws += this.reactions[i].rateLaw(species, x, concentrations);
        }
    return sumRateLaws;
  }
  
  
  //Clone method
  public ReactionSystem clone() {
	  return new ReactionSystem(this);
  }
  //Equals method
   public boolean equals(Object source){
	if (source == null)
		      return false;
	else if (source.getClass() != this.getClass())
		      return false;
	else {
		      ReactionSystem object = (ReactionSystem)source;
		      if((object.reactions.length != this.reactions.length)&&(object.species.length != this.species.length))
		    	  return false;
		      boolean other = true;
		      for(int i =0; i<object.reactions.length;i++) {
		    	  if(!(this.reactions[i].equals(object.reactions[i])))
		    		  other = false;
		      }
		      for(int i =0; i<object.species.length;i++) {
		    	  if(!(this.species[i].equals(object.species[i])))
		    		  other = false;
		      }
		      
		      if(object.desiredConversion != this.desiredConversion)
		    	  other = false;
		      if(object.phase != this.phase)
		    	  other = false;
		      if(object.productionRate != this.productionRate)
		    	  other = false;
		      if(object.totalInitialConcentration != this.totalInitialConcentration)
		    	  other = false;
		      
		      if( new Species(object.toMaximize) != new Species(this.toMaximize))
		    	  other = false;
		      if( new Species(object.toMinimize) != new Species(this.toMinimize))
		    	  other = false;
		      
		     return other;
		    }
   }
  
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
    if (this.desiredConversion !=-1)
      string += "\nThe desired conversion is: "+this.desiredConversion;
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
  
}// end of ReactionSystem class
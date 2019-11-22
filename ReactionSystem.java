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
  private int phase; // 1=liquid 2=gaz 3=solid 4=mixture
  private Species toMaximize;
  private Species toMinimize;
  
  //Constructor
  public ReactionSystem (Species [] species, Reaction[] reactions, int phase){
    this.species = new Species[species.length];
    for (int i =0; i<species.length;i++){
      this.species[i]=species[i];
    } 
    this.reactions = new Reaction [reactions.length];
    for (int i =0; i<reactions.length;i++){
      this.reactions[i]=reactions[i];
    }
    this.phase = phase;
  }
  
  public ReactionSystem (Species [] species, Reaction[] reactions, double totalInitialConcentration, int phase, Species toMaximize, Species toMinimize){
    this.species = new Species[species.length];
    for (int i =0; i<species.length;i++){
      this.species[i]=species[i];
    } 
    this.reactions = new Reaction [reactions.length];
    for (int i =0; i<reactions.length;i++){
      this.reactions[i]=reactions[i];
    }
    this.totalInitialConcentration=totalInitialConcentration;
    this.phase = phase;
    this.toMaximize = new Species (toMaximize);
    this.toMinimize = new Species (toMinimize);
  }
  
  
  public ReactionSystem (Species [] species, Reaction[] reactions, double desiredConversion, double productionRate, double totalInitialConcentration, 
                         int phase, Species toMaximize, Species toMinimize){
    this.species = new Species[species.length];
    for (int i =0; i<species.length;i++){
      this.species[i]=species[i];
    } 
    this.reactions = new Reaction [reactions.length];
    for (int i =0; i<reactions.length;i++){
      this.reactions[i]=reactions[i];
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
  }
  
  //Accessors
  public Species [] getSpecies(){
    return this.species;
  }
  public Reaction[] getReactions(){
    return this.reactions;
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
    return this.toMaximize;
  }
  public Species getToMinimize (){
    return this.toMinimize;
  }
  
  //Mutators  
  public void setSpecies (Species [] species){
    this.species = new Species [species.length];
    for (int i=0; i<species.length;i++){
      this.species[i]=species[i];
    }
  }
  public void setReactions(Reaction [] reactions){
    this.reactions = new Reaction [reactions.length];
    for (int i=0; i<reactions.length;i++){
      this.reactions[i]=reactions[i];
    }
  }  
  public void setConversion(double desiredConversion){
    this.desiredConversion = desiredConversion;
  }
  public void setProductionRate(double productionRate){
    this.productionRate= productionRate;
  }
  public void setTotalInitialConcentration(double totalInitialConcentration){
    this.totalInitialConcentration = totalInitialConcentration;
  }
  public void setPhase (int phase){
    this.phase = phase;
  }
  public void setToMaximize (Species toMaximize){
   this.toMaximize= new Species (toMaximize) ;
  }
  public void setToMinimize (Species toMinimize){
   this.toMinimize= new Species (toMinimize) ;
  }
  
  //Methods
  public double sumRateLawsPerSpecie(Species[] species, Species x, double[] concentrations){
    double sumRateLaws=0.0;
    for (int i=0; i<this.reactions.length;i++){
          sumRateLaws += this.reactions[i].rateLaw(species, x, concentrations);
        }
    return sumRateLaws;
  }
  
  
  /*  public boolean equals(Object source){
   if (source == null)
   return false;
   else if (source.getClass() != this.getClass())
   return false;
   else {
   Species object = (Species)source;
   return (this.name == object.name);
   private Species [] reactants;
   private Species [] species;
   private Reaction [] reactions;
   private double desiredConversion;
   private double productionRate;
   private double totalInitialConcentration; 
   private int phase; // 1=liquid 2=gaz 3=solid 4=mixture 
   } 
   }*/
  
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
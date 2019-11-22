package ReactorSimulator;
/*
 * 
 */
public class Reaction {
  
  // Instance variables
  private Species [] reactants;
  private Species [] products;
  private double [] coefficients;
  private boolean elementary; 
  private boolean reversible;
  private double equilibriumConstant;
  
  public int g_limitingReactantIndex=-1;
  public double g_rateConstantToBasis=0.0;
  public double [] g_reactantsCoefficientRatios;
  public double [] g_productsCoefficientRatios;
  
  //Constructor
  
  public Reaction (Species [] reactants, Species [] products, double [] coefficients,  boolean elementary, boolean reversible){
    if(reactants == null || products == null || coefficients == null)
    	throw new CouldNotConstructObjectException("Could not initiate Reaction");
    this.reactants = new Species [reactants.length];
    for (int i=0; i<reactants.length; i++){
    	if(reactants[i]==null)
    		throw new CouldNotConstructObjectException("Could not initiate Reaction");
      this.reactants[i] = new Species(reactants[i]);
    }
    this.products = new Species [products.length];
    for (int i=0; i<products.length; i++){
    	if(products[i] == null)
    		throw new CouldNotConstructObjectException("Could not initiate Reaction");
      this.products[i] = new Species(products[i]);
    }
    //Assuming coefficient of a species in a reaction can not be zero or negative
    this.coefficients = new double [coefficients.length];
    for (int i=0; i<coefficients.length; i++){
    	if(coefficients[i]<=0)
    		throw new CouldNotConstructObjectException("Could not initiate Reaction");
      this.coefficients[i] = coefficients[i];
    }     
    this.elementary = elementary;
    this.reversible = reversible;
  }
  
  public Reaction (Species [] reactants, Species [] products, double [] coefficients,  boolean elementary, boolean reversible, double equilibriumConstant){
    
	  if(reactants == null || products == null || coefficients == null)
	    	throw new CouldNotConstructObjectException("Could not initiate Reaction");
	    this.reactants = new Species [reactants.length];
	    for (int i=0; i<reactants.length; i++){
	    	if(reactants[i]==null)
	    		throw new CouldNotConstructObjectException("Could not initiate Reaction");
	      this.reactants[i] = new Species(reactants[i]);
	    }
	    this.products = new Species [products.length];
	    for (int i=0; i<products.length; i++){
	    	if(products[i] == null)
	    		throw new CouldNotConstructObjectException("Could not initiate Reaction");
	      this.products[i] = new Species(products[i]);
	    }
	    //Assuming coefficient of a species in a reaction can not be zero or negative
	    this.coefficients = new double [coefficients.length];
	    for (int i=0; i<coefficients.length; i++){
	    	if(coefficients[i]<=0)
	    		throw new CouldNotConstructObjectException("Could not initiate Reaction");
	      this.coefficients[i] = coefficients[i];
	    }     
	    this.elementary = elementary;
	    this.reversible = reversible;
	    this.equilibriumConstant= equilibriumConstant;
  }
  
  //Copy Constructor Just check source is null is sufficient no need for other conditions
  public Reaction (Reaction source) {
	  if(source == null)
		  throw new CouldNotConstructObjectException("Could not initiate Copy of Reaction");
    this.reactants = new Species [source.reactants.length];
    for (int i=0; i<source.reactants.length; i++){
      this.reactants[i] = source.reactants[i];
    }
    this.products = new Species [source.products.length];
    for (int i=0; i<source.products.length; i++){
      this.products[i] = source.products[i];
    }
    this.elementary = source.elementary;
    this.reversible = source.reversible;
    this.equilibriumConstant= source.equilibriumConstant;
  }
  
  //Accessors
  //TODO Check if you need to check constraints in accessors
  public Species [] getReactants (){
	  Species [] copy = new Species[this.reactants.length];
	  for(int i =0; i<copy.length;i++) {
		  copy[i] = new Species(this.reactants[i]);
	  }
	  
	  return copy;
  }
  public Species [] getProducts (){
	  Species [] copy = new Species[this.products.length];
	  for(int i =0; i<copy.length;i++) {
		  copy[i] = new Species(this.products[i]);
	  }
	  
    return copy;
  }
  public double [] getCoeffients (){
	  double [] copy = new double[this.coefficients.length];
	  for(int i =0; i<copy.length;i++) {
		  copy[i] = this.coefficients[i];
	  }
    return copy;
  }
  public boolean getElementary(){
    return this.elementary;
  }
  public boolean getReversible(){
    return this.reversible;
  }      
  public double getEquilibriumConstant(){
    return this.equilibriumConstant;
  }
  
  //Mutators
  
  public void setReactants (Species [] reactants){
	  if(reactants == null || reactants.length != this.reactants.length)
		  throw new CouldNotConstructObjectException("Could not set reactants");
    this.reactants = new Species [reactants.length];
    for (int i=0; i<reactants.length; i++){
    	if(reactants[i] == null)
    		throw new CouldNotConstructObjectException("Could not set reactants");
      this.reactants[i] = new Species(reactants[i]);
    }
  }
  public void setProducts (Species [] products){
	  if(products == null || products.length != this.products.length)
		  throw new CouldNotConstructObjectException("Could not set products");
    this.products = new Species [products.length];
    for (int i=0; i<products.length; i++){
    	if(products[i]==null)
    		throw new CouldNotConstructObjectException("Could not set products");
      this.products[i] = new Species(products[i]);
    }
  }
  public void setCoefficients (double [] coefficients){
	  if(coefficients == null || coefficients.length != this.coefficients.length)
		  throw new CouldNotConstructObjectException("Could not set coefficients");
    this.coefficients = new double [coefficients.length];
    for (int i=0; i<coefficients.length;i++){
    	if(coefficients[i]<=0)
    		throw new CouldNotConstructObjectException("Could not set coefficients");
      this.coefficients[i] = coefficients[i];
    }
  }
  public void setElementary(boolean elementary){
    this.elementary= elementary;
  }
  public void setReversible(boolean reversible){
    this.reversible= reversible;
  }   
  public void setEquilibriumConstant(double equilibriumConstant){
    this.equilibriumConstant=equilibriumConstant;
  }
  
  //Methods   
  
  public void findLimitingReactant (){
    double ratio = 0;
    double minRatio = 10000000;
    for (int i=0; i<this.reactants.length; i++){
      ratio = this.reactants[i].findInitialRate()/this.coefficients[i];
      if (ratio<minRatio){
        minRatio = ratio;
        this.g_limitingReactantIndex=i;
      }
    }
  }
  
  public double[] findReactantsCoefficientRatios(){    
    g_reactantsCoefficientRatios = new double [this.reactants.length];
    for (int i=0;i<this.reactants.length;i++){
      g_reactantsCoefficientRatios[i]=(this.coefficients[i])/(-1*this.coefficients[g_limitingReactantIndex]); 
    }
/*    for (int i=0; i<this.reactants.length;i++){
      System.out.println(g_reactantsCoefficientRatios[i]);
    }*/
    return g_reactantsCoefficientRatios;
  }
  public double[] findProductsCoefficientRatios(){
    g_productsCoefficientRatios = new double [this.products.length];
    for (int i=0;i<this.products.length;i++){
      g_productsCoefficientRatios[i]=(-1*this.coefficients[i+this.reactants.length])/(-1*this.coefficients[g_limitingReactantIndex]);
    }
/*    for (int i=0; i<this.products.length;i++){
      System.out.println(g_productsCoefficientRatios[i]);
    }*/
    return g_productsCoefficientRatios;
  }     
  
  
  public double convertRateConstantToBasis(){
    int speciesIndexAvailableRateConstant=-1;
    boolean speciesIndexAvailableRateConstantIsReactant = false;
    for (int i=0; i<this.reactants.length; i++){
      if (this.reactants[i].getRateConstant()!=-1.0){
        speciesIndexAvailableRateConstant =i;
        speciesIndexAvailableRateConstantIsReactant = true;
      }
    }
    if (speciesIndexAvailableRateConstant==-1){
      for (int i=0; i<this.products.length; i++){
        if (products[i].getRateConstant()!=-1.0){
          speciesIndexAvailableRateConstant =i+this.reactants.length;
        }
      }
    }
    
   //   System.out.println("Available Rate Constant:"+speciesIndexAvailableRateConstant);
   //   System.out.println("Available Rate Constant is reactant:"+speciesIndexAvailableRateConstantIsReactant);
    
    if (g_limitingReactantIndex == speciesIndexAvailableRateConstant) {
      g_rateConstantToBasis= reactants[speciesIndexAvailableRateConstant].getRateConstant();
    }
    else if (this.elementary && speciesIndexAvailableRateConstantIsReactant) //review if necessary to have elementary
      g_rateConstantToBasis= ((reactants[speciesIndexAvailableRateConstant].getRateConstant()*this.coefficients[g_limitingReactantIndex])/this.coefficients[speciesIndexAvailableRateConstant]); 
    else if (this.elementary && !speciesIndexAvailableRateConstantIsReactant)
      g_rateConstantToBasis= ((products[speciesIndexAvailableRateConstant].getRateConstant()*this.coefficients[g_limitingReactantIndex])/this.coefficients[speciesIndexAvailableRateConstant]);        
    
    return g_rateConstantToBasis;     
//  else 
    //   g_rateConstantToBasis= // find k value for basis if not elementary
  }
  
  //must be changed to use role variable
  public double rateLaw (Species [] species, Species x, double[] concentrations){
    double k=0.0;
    double rateLaw =0.0;
    double rateLawPart2=0.0;
    boolean xIsReactant= false; 
    int concentrationsIndex=-1;
    
    for (int j=0;j<species.length;j++){
      if (species[j].equals(x)){
        concentrationsIndex=j;
      }
    }
    
    for (int i=0;i<this.reactants.length;i++){
      if (this.reactants[i].equals(x)){
        xIsReactant = true;
        k = this.g_reactantsCoefficientRatios[i]*this.g_rateConstantToBasis;
        rateLaw = k;
        for (int j=0;j<this.reactants.length;j++){       
          for (int z=0;z<species.length;z++){
            if (this.reactants[j].equals(species[z])){
              concentrationsIndex=z;
            }
          }         
          rateLaw=rateLaw*Math.pow(concentrations[concentrationsIndex],this.coefficients[j]);
        }
        if (reversible){
          rateLawPart2=k;
          for (int j=0;j<this.products.length;j++){ 
            for (int z=0;z<species.length;z++){
              if (this.products[j].equals(species[z])){
                concentrationsIndex=z;
              }
            }  
            rateLawPart2=rateLawPart2*(Math.pow(concentrations[concentrationsIndex],this.coefficients[j+this.reactants.length]));
          }    
          rateLaw=rateLaw-(rateLawPart2/this.equilibriumConstant);
        }
      }
    }
    
    if (!xIsReactant) {
      for (int i=0;i<products.length;i++){  
        if (this.products[i].equals(x)){
          k=this.g_productsCoefficientRatios[i]*this.g_rateConstantToBasis;
          rateLaw = k;
          for (int j=0;j<this.reactants.length;j++){    
            for (int z=0;z<species.length;z++){
              if (this.reactants[j].equals(species[z])){
                concentrationsIndex=z;
              }
            }
            rateLaw=rateLaw*Math.pow(concentrations[concentrationsIndex],this.coefficients[j]);
          }
          if (reversible){
            rateLawPart2=k;
            for (int j=0;j<this.products.length;j++){        
              for (int z=0;z<species.length;z++){
                if (this.products[j].equals(species[z])){
                  concentrationsIndex=z;
                }
              }    
              rateLawPart2=rateLawPart2*(Math.pow(concentrations[concentrationsIndex],this.coefficients[j+this.reactants.length]));
            }    
            rateLaw=rateLaw-(rateLawPart2/this.equilibriumConstant);
            } 
          }
        }
     }
  return rateLaw;
  }
    
    /*
     * public boolean equals(Object source){
     if (source == null)
     return false;
     else if (source.getClass() != this.getClass())
     return false;
     /*    else {
     Species object = (Species)source;
     return (this.name == object.name);
     private Species [] reactants;
     private Species [] products;
     private double [] coefficients;
     private boolean elementary; 
     private boolean reversible;
   private double equilibriumConstant;
   } 
   }
   */
  
  //Clone Method
  public Reaction clone() {
	  return new Reaction(this);
  }
  
  //Equals Method, Need to check all of its instances
  public boolean equals(Object source){
	    if (source == null)
	      return false;
	    else if (source.getClass() != this.getClass())
	      return false;
	    else {
	      Reaction object = (Reaction)source;
	      if((object.reactants.length != this.reactants.length)&&(object.products.length != this.products.length)
	    		  &&(object.coefficients.length != this.coefficients.length))
	    	  return false;
	      boolean other = true;
	      for(int i =0; i<object.reactants.length;i++) {
	    	  if(!(this.reactants[i].equals(object.reactants[i])))
	    		  other = false;
	      }
	      for(int i =0; i<object.products.length;i++) {
	    	  if(!(this.products[i].equals(object.products[i])))
	    		  other = false;
	      }
	      for(int i =0; i<object.coefficients.length;i++) {
	    	  if(!(this.coefficients[i] == (object.coefficients[i])))
	    		  other = false;
	      }
	      if(object.elementary != this.elementary)
	    	  other = false;
	      if(object.reversible != this.reversible)
	    	  other = false;
	      if(object.equilibriumConstant != this.equilibriumConstant)
	    	  other = false;
	      
	     return other;
	    }
  }
  
  public String toString (){
    String string ="";
    string = "\nThis reaction contains the following reactants: ";
    for (int i =0; i<this.reactants.length;i++){
      string += "\n"+this.reactants[i];
    }
    string += "\nThis reaction contains the following products: ";
    for (int i =0; i<this.products.length;i++){
      string += "\n"+this.products[i];
    } 
    string += "\nTheir coefficients are: ";
    for (int i =0; i<this.coefficients.length;i++){
      string += "\n"+this.coefficients[i];
    }    
    if (this.elementary){
      string +="\nThe reaction is elementary";
    }
    else string +="\nThe reaction is not elementary";
    if (this.reversible){
      string +="\nThe reaction is reversible and the equilibrium constant is: "+this.equilibriumConstant;
    }
    else string +="\nThe reaction is not reversible";
    string+="\n";
    return string;
  } 
  
  
}//end of Reaction class
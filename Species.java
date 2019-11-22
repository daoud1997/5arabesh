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
  //Assuming maxRatechangeflow, transport, shell cant be less than zero CHange it if needed
  //Constructor
  public Species (char name, double initialFlowRate,double maxRateChangeFlow) {
	  if(name == ' '|| initialFlowRate<0|| maxRateChangeFlow<0)
		  throw new CouldNotConstructObjectException("Could not construct Species");
    this.name = name; 
    this.initialFlowRate = initialFlowRate;
    this.maxRateChangeFlow = maxRateChangeFlow;
  }
  public Species (char name, double initialFlowRate, double rateConstant, double maxRateChangeFlow) {
	  if(name == ' '|| initialFlowRate<0|| maxRateChangeFlow<0 || rateConstant<0)
		  throw new CouldNotConstructObjectException("Could not construct Species");
    this.name = name; 
    this.initialFlowRate = initialFlowRate;
    this.rateConstant= rateConstant;
    this.maxRateChangeFlow = maxRateChangeFlow;
  }
  
  public Species (char name, double initialFlowRate, double initialConcentration, double rateConstant, double maxRateChangeFlow,
                  double transportCoefficient, double shellSideConcentration) {
	  if(name == ' '|| initialFlowRate<0|| maxRateChangeFlow<0|| transportCoefficient<0|| shellSideConcentration<0)
		  throw new CouldNotConstructObjectException("Could not construct Species");
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
	  if(source == null)
		  throw new CouldNotConstructObjectException("Could not construct copy Species");
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
  public boolean setName (char name){
	  if(name == ' ')
		  return false;
    this.name = name;
    return true;
  }
  public boolean setInitialFlowRate (double initialFlowRate){
	  if(initialFlowRate <0 )
		  return false;
    this.initialFlowRate = initialFlowRate;
    return true;
  } 
  public boolean setInitialConcentration (double initialConcentration){
	  if(initialConcentration<0)
		  return false;
    this.initialConcentration= initialConcentration;
    return true;
  }
  public boolean setRateConstant (double rateConstant){
	  if(rateConstant < 0)
		  return false;
    this.rateConstant = rateConstant;
    return true;
  }
  public boolean setMaxRateChangeFLow(double maxRateChangeFlow){
	  if(maxRateChangeFlow<0)
		  return false;
    this.maxRateChangeFlow = maxRateChangeFlow;
    return true;
  }
  public boolean setTransportCoefficient (double transportCoefficient){
	  if(transportCoefficient<0)
		  return false;
    this.transportCoefficient=transportCoefficient;
    return true;
  }
  public boolean setShellSideConcentration(double shellSideConcentration){
	  if(shellSideConcentration<0)
		  return false;
    this.shellSideConcentration= shellSideConcentration;
    return true;
  }
  // Methods
  
  public double findInitialRate (){
    if (this.initialFlowRate !=-1.0)
      return this.initialFlowRate;
    else return initialConcentration;     
  }
 
  //Equals Method
  public boolean equals(Object source){
    if (source == null)
      return false;
    else if (source.getClass() != this.getClass())
      return false;
    else {
      Species object = (Species)source;
      return ((object.name == this.name) && (object.initialConcentration == this.initialConcentration) && (object.rateConstant == this.rateConstant)&&
    		  (object.maxRateChangeFlow == this.maxRateChangeFlow) && (object.transportCoefficient == this.transportCoefficient) && (object.shellSideConcentration == this.shellSideConcentration) );
    }
  }
  //Clone Method
  public Species clone() {
	  return new Species(this);
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
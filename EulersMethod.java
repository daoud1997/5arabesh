package ReactorSimulator;
/*
 * 
 */
public class EulersMethod extends ODESolver {
  
  // Constructor 
  public EulersMethod(ODEEquation equation, double h) {
    super(equation, h);
  }// end of Constructor
  // Copy Constructor
  public EulersMethod(EulersMethod source) {
    super(source); 
  }// end of Copy Constructor
  
  /** The calling object MUST implement the ODEEquation interface */
  public double solveODE(double xInit, double yInit, double xFinal) {
    if(xInit >= xFinal) 
      throw new RuntimeException("Critical Error: The variable xInit must be smaller than the variable xFinal!");
    
    double x = xInit;
    double y = yInit;
    double h = this.getH();
    do {
      if(this.getH() > (xFinal - x)) 
        h = xFinal - x;
      else
        h = this.getH();
      
      y += this.getODEEquation().equation(x, y)*h;
      x += h;
    } while(x < xFinal); 
    
    return y;
  }// end of solveODE method
  
  public boolean equals(Object object) {
    if (!super.equals(object)) 
      return false;
    else 
      return this.getClass() == object.getClass();
  }// end of equals method
  
  public EulersMethod clone() throws CouldNotConstructObjectException {
    return new EulersMethod(this);
  }// enf of clone method
  
}

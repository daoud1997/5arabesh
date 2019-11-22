package ReactorSimulator;
/*
 * 
 */
public abstract class ODESolver {
  
  //Instance Variables
  private ODEEquation equation;
  private double h;
  
  // Constructor
  public ODESolver(ODEEquation equation, double h) {
    if(equation != null) 
      this.equation = equation;
    else 
      throw new CouldNotConstructObjectException("ODESolver");
    
    if(h <= 0) 
      throw new RuntimeException("Critical Error: The variable h must be above zero!");
    else 
      this.h = h;
  }//end of Constructor
  
  // Copy Constructor
  public ODESolver(ODESolver source) {
    this.equation = source.equation.clone();
    this.h = source.h;
  }// end of Copy Constructor
  
// Accessors 
  public ODEEquation getODEEquation() {
    return this.equation;
  }// end of getODEEquation method
  
  public double getH() {
    return this.h;
  }// end of getH method
  
  
  // Mutators
  public void setODEEquation(ODEEquation equation) {
    if(equation != null) 
      this.equation = equation;
    else 
      throw new CouldNotConstructObjectException("ODESolver");
  }// end of setODEEquation method
  
  public void setH(double h) {
    if(h <= 0) 
      throw new RuntimeException("The variable h must be above zero!");
    else 
      this.h = h;
  }// end of setH method
  
  public boolean equals(Object source) {
    if (source == null)
      return false;
    else if (source.getClass() != this.getClass())
      return false;
    else {
      ODESolver object = (ODESolver)source;
      return ((this.equation.equals(object.equation)) && (this.h == object.h));
    }
  }// end of equals method
  
  public String toString(){
    return "The equation :: " + this.equation.toString() + " | H :: " + this.h;
  }// end of toString method
  
  public abstract ODESolver clone() throws CouldNotConstructObjectException;
  
  public abstract double solveODE(double xInit, double yInit, double xFinal);
  
}// end of ODESolver class

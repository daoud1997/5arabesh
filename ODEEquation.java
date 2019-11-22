package ReactorSimulator;
/*
 * 
 */
public interface ODEEquation {
  
  public double equation(double x, double y);
  
  public ODEEquation clone() throws CouldNotConstructObjectException;
  
}// end of ODEEquation interface

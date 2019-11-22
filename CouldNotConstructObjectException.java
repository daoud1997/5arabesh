package ReactorSimulator;
/*
 * 
 */

public class CouldNotConstructObjectException extends RuntimeException {
  
  /* The following line is necessary to avoid eclipse errors */
  private static final long serialVersionUID = 1L; 
  
  // Constructor 1
  public CouldNotConstructObjectException() {
    super("Critical Error: Could not construct object!"); 
  }// end of Constructor 1
  
  // Constructor 2
  public CouldNotConstructObjectException(String message) { 
    super("Critical Error: " + message + " object could not be constructed!");
  }// end of Constructor 2
  
}// end of CouldNotConstructObjectException class

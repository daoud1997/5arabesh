package ReactorSimulator;
/*
 * RungeKutta45 implements a RKF 4/5 ODE solver with variable step size
 * https://math.okstate.edu/people/yqwang/teaching/math4513_fall11/Notes/rungekutta.pdf
 */
public class RungeKutta45 extends ODESolver{
  
  public static final double ERROR_TOLERANCE=0.000001; //epsilon
// Constructor
  public RungeKutta45(ODEEquation equation, double h) {
    super(equation, h);
  }// end of constructor method
  
// Copy Constructor  
  public RungeKutta45 (RungeKutta45 source){
    super(source);
  }// end of copy constructor method
  
  // Clone method
  public RungeKutta45 clone() throws CouldNotConstructObjectException {
    return new RungeKutta45(this);
  }// end of clone method
  
// Methods
  public double solveODE(double xInit, double yInit, double xFinal) {
    
    if(xInit >= xFinal) 
      throw new RuntimeException("Critical Error: The variable xInit must be smaller than the variable xFinal!");
    
    double k1, k2, k3, k4, k5, k6; 
    double x = xInit;
    double y = yInit;
    double h=this.getH();
    double y1;
    double y2;
    double r;
    double delta;
    double [] coeff_y1 = new double[]{(25.0/216.0), (1408.0/2565.0), (2197.0/4104.0), (1.0/5.0)};
    double [] coeff_y2 = new double[]{(16.0/135.0), (6656.0/12825.0), (28561.0/56430.0), (9.0/50.0), (2.0/55.0)};
    double [] a = new double []{(1.0/4.0), (3.0/8.0), (12.0/13.0), (1.0), (1.0/2.0)};
    double [] b = new double []{(1.0/4.0),(3.0/32.0),(1932.0/2197.0), (39.0/216.0), (8.0/27.0)};
    double [] c = new double []{(9.0/32.0),(7200.0/2197.0),(8.0), (2.0)};
    double [] d = new double []{(7296.0/2197.0),(3680.0/513.0),(3544.0/2565.0)};
    double [] e = new double []{(845.0/4104.0),(1859.0/4104.0)};
    double [] f = new double []{(11.0/40.0)};  
    
    do {
      
      if(h > (xFinal - x)) {
        h = xFinal - x;
      }
      
      k1 = this.getODEEquation().equation(x, y)*h;
      //System.out.println("k1: "+k1);
      k2 = this.getODEEquation().equation((x+(a[0]*h)),(y+(b[0]*k1)))*h;
      //System.out.println("k1: "+k2);
      k3 = this.getODEEquation().equation((x+(a[1]*h)),(y+(b[1]*k1)+(c[0]*k2)))*h;
      //System.out.println("k1: "+k3);   
      k4 = this.getODEEquation().equation((x+(a[2]*h)),(y+(b[2]*k1)-(c[1]*k2)+(d[0]*k3)))*h;
      //System.out.println("k1: "+k4);   
      k5 = this.getODEEquation().equation((x+(a[3]*h)),(y+(b[3]*k1)-(c[2]*k2)+(d[0]*k3)-(e[0]*k4)))*h;
      //System.out.println("k1: "+k5);    
      k6 = this.getODEEquation().equation((x+(a[4]*h)),(y-(b[4]*k1)+(c[3]*k2)-(d[0]*k3)+(e[1]*k4)-(f[0]*k5)))*h;  
      //System.out.println("k1: "+k6);    
      
      y1 = y+(coeff_y1[0]*k1)+(coeff_y1[1]*k3)+(coeff_y1[2]*k4)-(coeff_y1[3]*k5);
      //  System.out.println("y1: "+y1);
      y2 = y+(coeff_y2[0]*k1)+(coeff_y2[1]*k3)+(coeff_y2[2]*k4)-(coeff_y2[3]*k5)+(coeff_y2[4]*k6);       
//System.out.println("y2: "+y2);
      r = Math.abs(y1-y2)/h;
      //  System.out.println("R: "+r);
      delta = 0.84*Math.pow((ERROR_TOLERANCE/r),0.25);
      //  System.out.println("delta: "+delta);
      if (r<=ERROR_TOLERANCE){
        x=x+h;
        y=y1;
        //System.out.println("y:"+y);
        h=delta*h;
      } 
      else{
        h=delta*h;
      } 
    } while(x < xFinal); 
    // System.out.println("y:"+y);
    return y;
  }// end of solveODE method
  
  public boolean equals(Object source) {
    if (source == null)
      return false;
    else if (source.getClass() != this.getClass())
      return false;
    else {
      ODESolver object = (ODESolver)source;
      return ((this.getODEEquation().equals(object.getODEEquation())) && (this.getH() == object.getH()));
    }
  }// end of equals method
  
  public String toString(){
    return "The equation :: " + this.getODEEquation().toString() + " | H :: " + this.getH();
  }// end of toString method
  
}// end of RungeKutta45 class
package ReactorSimulator;
/*
 * 
 */
import java.util.*;
import java.io.*;

public class Simulator {
  
  
  public static void main (String [] args){
    
 //   List userData = new ArrayList<Double>(); // increase capacity as needed
    int select=0;
    ReactorSystem reactorSystem;
    
    //Initializing local variables
    double [] userData = new double [1000]; // increase capacity as needed
    Scanner scan = new Scanner (System.in);
    System.out.println("This simulator provides the design and optimization of a tubular reactor system.");
    String input="";
    
    //Asking user how he/she would like to input parameters
    System.out.println("Please ensure a .txt file is available in the suggested format before proceeding.\n Once a .txt file is available, press any key to continue.");
    input=scan.next();
    
    //Based on above user answer, inputing parameters
    fromTextFile(userData);
    reactorSystem = new ReactorSystem (initializeObjects(userData));
    
    //Printing the inputed values for the user so he/she can confirm that data was correctly inputed 
    System.out.println("Based on the data entered, your Reactor System is as follows: \n"+reactorSystem);
    
    //Asking the user if he/she would like to calculate reactor system volumes, optimize system or exit the program
    do{  
      try{
        System.out.println("Please select one of the following options:\n1)Determine the molar flow rates of all chemical species involved\n2)Determine the volume for maximized desired product rate\n3)Determine the volume for maximized product selectivity\n4)Given a specified conversion from an unspecified reactor, determine if a series of plug flow reactors of varying size can achieve the same performance by modifying the inter-stage feed conditions\n5)Exit program");
        input=scan.next();
        select=Integer.parseInt(input);}
      catch (NumberFormatException e){
        System.out.println("Please try entering a valid number.");}
      catch (InputMismatchException e){
        System.out.println("Please try entering a valid number.");}
    }while(select>5 || select<1);   
    
    selectComputation ();
    
    //Asking until the user chooses to exit the program if he/she would like to execute the same functions as before with an added possibility of outputing data to a .csv file
    do{
      do{  
        try{
          System.out.println("Please select one of the following options:\n1)Determine the molar flow rates of all chemical species involved\n2)Determine the volume for maximized desired product rate\n3)Determine the volume for maximized product selectivity\n4)Given a specified conversion from an unspecified reactor, determine if a series of plug flow reactors of varying size can achieve the same performance by modifying the inter-stage feed conditions\n5)Save results in .csv file\n6)Exit program");
          input=scan.next();
          select=Integer.parseInt(input);}
        catch (NumberFormatException e){
          System.out.println("Please try entering a valid number.");}
        catch (InputMismatchException e){
          System.out.println("Please try entering a valid number.");}
      }while(select>6 || select<1);           
      selectComputation ();
    }while (select!=6);
  }//end of main method
  
  public static ReactorSystem initializeObjects (double [] userData){
    //Initialize objects with user provided parameters
  //  initializeObjects (userData);
    int numberReactions=0;
    int numberSpecies=0;
    Reaction [] reactions = new Reaction [numberReactions];
    Species [] species = new Species [numberSpecies];
    // ReactionSystem reactionSystem = new ReactionSystem ();      
    int numberReactors=0;
    TubularReactor [] reactors = new TubularReactor [numberReactors];
    int reactorType=-1;
    for (int i=0; i<reactors.length;i++){
      if (reactorType ==1){
        //   reactors [i] = new PlugFlowReactor ();
      }
      else if (reactorType == 2){
        //    reactors [i] = new MembraneReactor ();
      }
    }
    
    return new ReactorSystem ();
    
    // .... continue initilizations and passing userdata as constructor parameters
  }    
  
  public static void selectComputation (){
    /*    // Based on user selection, calculating the correct variable
     if (select==1){
     //System.out.printf("\nThe molar flow rates are : %.3f m3\n", reactorSystem.printMolarFlowRates()); 
     }
     if (this.select==2){
     
     }
     if (this.select==3){
     
     }
     if (this.select==4){
     
     }
     else System.exit(0);
     */
  }   
  
  public static void fromTextFile(double [] userData) throws NumberFormatException {
    Scanner scan = new Scanner (System.in);
    //Prompt user for input file name
    System.out.println("Please enter input file name followed by .txt: ");
    String fileName = scan.next();
    System.out.println("Reading from file...");
    // This will reference one line at a time
    String line = null;
    try {
      // FileReader reads text files in the default encoding
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
      while(true){
        line = bufferedReader.readLine();
        if (line==null) break;
        for (int i=0; i<18;i++){ 
          line = bufferedReader.readLine();  
          userData[i]=Double.parseDouble(line);
          line = bufferedReader.readLine();
        }
      }       
      // Closing file
      bufferedReader.close();         
    }//Error handling if file cannot be found
    catch(FileNotFoundException ex) {System.out.println("Unable to open file '" +fileName + "'");}
    //Error handling if trouble reading file
    catch(IOException ex) {System.out.println("Error reading file '"+ fileName + "'");}
  }//end of fromTextFile method
  
  public static void toCSVFile(TubularReactor tubularReactor) throws IOException{
    Scanner scan = new Scanner (System.in); 
    //Prompting user for output file name
    System.out.println("Please enter output file name followed by '.csv': ");
    String fileName = scan.next();
    System.out.println("Writing to file...");    
    
    // Creating a Workbook
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new File(fileName));
    } catch (FileNotFoundException e) {System.out.println("Unable to write to file '" +fileName+ "'");}
    //Retrieving data to write to .csv file
    //   double [][] data = tubularReactor.get_g_data();
    String [] headings= new String []{"V (m3)","Fa (mol/s)","Fb (mol/s)", "Fc (mol/s)", "Fd(mol/s)", "Fe(mol/s)","Ft (mol/s)","Pa (mol/m3)","Pb (mol/m3)","Pc (mol/m3)","Pd (mol/m3)", "Pe (mol/m3)", 
      "Ca (mol/m3)","Cb (mol/m3)","Cc (mol/m3)","Cd (mol/m3)", "Ce (mol/m3)", "r1basis (mol/kg.s)","r2basis (mol/kg.s)","r3basis (mol/kg.s)","ra (mol/kg.s)","rb (mol/kg.s)","rc (mol/kg.s)", "rd (mol/kg.s)","re (mol/kg.s)",
      "Vi+1 (m3)", "error (%)", "selectivity"}; // check k constant units and verify all columns included
    //Writing data to Workbook
    //  for (int j=0;j<data[0].length;j++){
    //    pw.print(String.format(headings[j]+"\n"));
    //   for(int i=0; i<data.length; i++){
    //     pw.print(data[i][j]);
    //    pw.print("\n");}}
    //Closing Workbook
    pw.close();
  }//end of toCSVFile method
  
}//end of Simulator class
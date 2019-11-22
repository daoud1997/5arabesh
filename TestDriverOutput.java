package ReactorSimulator;
/*
 * 
 */

import java.util.*;
import java.io.*;

public class TestDriverOutput {

  public static void main (String [] args)throws IOException{
    
    Species a = new Species ('A', 10.0, -1.0, 100.0, 0.1, -1.0, -1.0);
    Species b = new Species ('B', 20.0, -1.0, -1.0, 0.1, -1.0, -1.0);
    Species c = new Species ('C', 0.0, -1.0, 1500.0, 0.1, -1.0, -1.0);
    Species d = new Species ('D', 0.0, -1.0, -1.0, 0.1, 5, -1.0);
    Species e = new Species ('E', 0.0, -1.0, -1.0, 0.1, -1.0, -1.0);
    
    Reaction reaction1 = new Reaction (new Species[]{a, b}, new Species[]{c, d}, new double[]{1.0, 2.0, 1.0, 1.0},true, true, 1000);
    Reaction reaction2 = new Reaction (new Species[]{a, c}, new Species[]{e}, new double[]{2.0, 2.0, 1.0},true, false, -1.0);    

    Reaction [] reactions = new Reaction[]{reaction1, reaction2};
     
    ReactionSystem reactionSystem = new ReactionSystem (new Species[]{a, b, c, d, e}, new Reaction[]{reaction1, reaction2}, -1.0, -1.0, -1.0, 2, c, a);    
    
    TubularReactor reactor = new MembraneReactor (reactionSystem, -1.0, -1.0, 0, 100.0, d, false, true);      
    
    ReactorSystem reactorSystem = new ReactorSystem (new TubularReactor[] {reactor});
    
 //   System.out.println(reaction1);
  //  System.out.println(reaction2);
    
   // System.out.println(reactionSystem);  
   // System.out.println(reactor); 
    
   // System.out.println(a.findInitialRate());
   // System.out.println(b.findInitialRate());
   // System.out.println(c.findInitialRate());
    
     reaction1.findLimitingReactant();
     
   // System.out.println("Limiting reactant index: "+reaction1.g_limitingReactantIndex);
    
    reaction2.findLimitingReactant();
     
   // System.out.println("Limiting reactant index: "+reaction2.g_limitingReactantIndex);
    
    
     reaction1.findReactantsCoefficientRatios();
     for (int i=0; i<reaction1.getReactants().length;i++){
    //System.out.println("Reactant Coefficient Ratio: "+reaction1.g_reactantsCoefficientRatios[i]); 
     }
    
    reaction1.findProductsCoefficientRatios();
     for (int i=0; i<reaction1.getProducts().length;i++){
   //  System.out.println("Product Coefficient Ratio: "+reaction1.g_productsCoefficientRatios[i]); 
     }
     
      reaction2.findReactantsCoefficientRatios();
     for (int i=0; i<reaction2.getReactants().length;i++){
    //System.out.println("Reactant Coefficient Ratio: "+reaction2.g_reactantsCoefficientRatios[i]); 
     }
    
    reaction2.findProductsCoefficientRatios();
     for (int i=0; i<reaction2.getProducts().length;i++){
    // System.out.println("Product Coefficient Ratio: "+reaction2.g_productsCoefficientRatios[i]); 
     }   
    
     reaction1.convertRateConstantToBasis();
   //  System.out.println("Rate Constant to basis: "+reaction1.g_rateConstantToBasis);
    
     reaction2.convertRateConstantToBasis();
    // System.out.println("Rate Constant to basis: "+reaction2.g_rateConstantToBasis);
     
   //  System.out.println(reactor.findInitialTotalFlowRate());
    // System.out.println(reactor.findInitialTotalConcentration());
     
    reactor.solveSystem();
    
   toCSVFile(reactorSystem);  
    
  }// end of main method

  public static void toCSVFile(ReactorSystem reactorSystem) throws IOException{
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
    ArrayList data = reactorSystem.getData();
    String [] headings= new String []{"V","Fa","Fb", "Fc", "Fd", "Fe","Ft",
      "Ca","Cb","Cc","Cd", "Ce", "r1basis","r2basis","r3basis","ra","rb","rc", "rd","re",
      "Vnext", "error", "selectivity"}; // check k constant units and verify all columns included
    //Writing data to Workbook   
    ArrayList<ArrayList<Double>> reactor;
    for (int j=0;j<headings.length;j++){
      pw.print(String.format(headings[j]+"\n"));
    }
      for(int i=0; i<data.size(); i++){
        reactor = (ArrayList<ArrayList<Double>>)data.get(0);
        for (int j=0; j<reactor.size();j++){
        pw.print(reactor.get(j));
        pw.print("\n");
      }
    }
    System.out.println("Writing complete.");  
    //Closing Workbook
    pw.close();
  }//end of toCSVFile method
}
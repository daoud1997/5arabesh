package ReactorSimulator;
/* 
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public  class CSVReaderWriter {
  
  public static void main (String [] args) throws FileNotFoundException, IOException {
    System.out.println("Reading from CSV file..."); 
    List userData = readCSV();
    print(userData);
    //  List<UserData> userData = readDataFromCSV ("data.csv");   
  }//end of main method
  
  public static List readCSV() throws FileNotFoundException, IOException {
    List userData = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader("data.csv"));
    String line = br.readLine(); // Reading header, Ignoring
    
    while ((line = br.readLine()) != null && !line.isEmpty()) {
      String[] fields = line.split(",");
      String name = fields[0];
      String capital = fields[1];
      String currency = fields[2];
      String currency = fields[3];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      String currency = fields[2];
      //create objects
      //Country nation = new Country(name, capital, currency); 
      //countries.add(nation);           
    }
    br.close();
    return userData;
  }// end of readCSV method
  
  public static void print(List userData){ 
    System.out.println("========================"); 
    for (String data : userData) { 
      System.out.println(data); 
    } 
    System.out.println("========================"); 
  }//end of print method
  
  public static List writeCSV() throws FileNotFoundException, IOException {
    // Our example data
    List<List<String>> rows = Arrays.asList(
                                            Arrays.asList("Jean", "author", "Java"),
                                            Arrays.asList("David", "editor", "Python"),
                                            Arrays.asList("Scott", "editor", "Node.js")
                                           );
    
    FileWriter csvWriter = new FileWriter("new.csv");
    csvWriter.append("Name");
    csvWriter.append(",");
    csvWriter.append("Role");
    csvWriter.append(",");
    csvWriter.append("Topic");
    csvWriter.append("\n");
    
    for (List<String> rowData : rows) {
      csvWriter.append(String.join(",", rowData));
      csvWriter.append("\n");
    }
    
    csvWriter.flush();
    csvWriter.close();
    
    /* List userData = new ArrayList<>();
     BufferedReader br = new BufferedReader(new FileReader("data.csv"));
     String line = br.readLine(); // Reading header, Ignoring
     
     while ((line = br.readLine()) != null && !line.isEmpty()) {
     String[] fields = line.split(",");
     String name = fields[0];
     String capital = fields[1];
     String currency = fields[2];
     String currency = fields[3];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     String currency = fields[2];
     //create objects
     //Country nation = new Country(name, capital, currency); 
     //countries.add(nation);           
     }
     br.close();
     return userData; */
  }// end of readCSV method
  
  public static void writeCSV(TubularReactor tubularReactor) throws IOException{
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
  
}//end of CSVReader class
package ReactorSimulator;

public class TestDriver{
  
  public static void main (String [] args){
    
    Species [] reactants = new Species [2];
    reactants[0] = new Species ('A', 15.0, -1.0, 100.0, 0.1, -1.0, -1.0);
    reactants[1] = new Species ('B', 15.0, -1.0, -1.0, 0.1, -1.0, -1.0);
    Species [] products = new Species [1];
    products[0] = new Species ('C', 0.0, -1.0, -1.0, 0.1, -1.0, -1.0);
    
    Species [] species = new Species [3];
    species [0] = reactants[0];
    species [1] = reactants[1];
    species [2] = products[0];
    
    double [] coefficients = new double [species.length];
    
    coefficients [0] = 1;
    coefficients [1] = 2;
    coefficients [2] = 1;
    
    Reaction reaction = new Reaction (reactants, products, coefficients, true, false, -1.0);
    
    Reaction [] reactions = new Reaction [1];
    reactions[0] = reaction;
    
    ReactionSystem reactionSystem = new ReactionSystem (species, reactions, -1.0, -1.0, -1.0, 1, null, null);    
   
    TubularReactor reactor = new PlugFlowReactor (reactionSystem, -1.0, -1.0, 600.0, 120.0);  
    
    double [] concentrations = new double [3];
    concentrations[0]=0.125;
    concentrations[1]=0.125;
    concentrations[2]=0;
    
    //  System.out.println(species[0]);
    //  System.out.println(species[1]);
    //  System.out.println(species[2]);
    
    //  System.out.println(reaction);
    
    //  System.out.println(reactionSystem);  
    //  System.out.println(reactor); 
    
    //   System.out.println(species[0].findInitialRate());
    //   System.out.println(species[1].findInitialRate());
    //   System.out.println(species[2].findInitialRate());
    
    /*      System.out.println(species[0].equals(reactor));
     System.out.println(species[0].equals(species[1]));
     System.out.println(species[0].equals(species[2]));
     System.out.println(species[1].equals(species[1]));
     System.out.println(species[1].equals(species[0]));
     System.out.println(species[1].equals(species[2]));
     System.out.println(species[2].equals(species[2]));
     System.out.println(species[2].equals(species[0]));
     System.out.println(species[2].equals(species[1])); */
    reaction.findLimitingReactant();
    // System.out.println("Limiting reactant index: "+reaction.g_limitingReactantIndex);
    
    reaction.findReactantsCoefficientRatios();
    /*for (int i=0; i<reactants.length;i++){
     System.out.println("Reactant Coefficient Ratio: "+reaction.g_reactantsCoefficientRatios[i]); 
     }
     */
    reaction.findProductsCoefficientRatios();
    /* for (int i=0; i<products.length;i++){
     System.out.println("Product Coefficient Ratio: "+reaction.g_productsCoefficientRatios[i]); 
     }
     */    
    
    
    reaction.convertRateConstantToBasis();
    // System.out.println("Rate Constant: "+reaction.)
    //  System.out.println("Rate Constant to basis: "+reaction.g_rateConstantToBasis);
    
    
    //  System.out.println(reactor.findInitialTotalFlowRate());
    //   System.out.println(reactor.findInitialTotalConcentration());
    
    reactor.solveSystem();
    
    
//    for (int i=0;i<
    //   System.out.prinln(reactor.g_data[][]);
    
    //   System.out.println(reactionSystem.sumRateLawsPerSpecie(species[2], concentrations));
    
  }
  
}
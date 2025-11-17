package lab3;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UniverseStats {
    public static void main(String[] args) {
        System.out.println("~ Universe Classification Statistics ~\n");
        
        // load all aliens
        List<AlienSpecies> aliens = AlienInfo.aliens("aliens.json");
        
        // classify by universe
        AlienClassifier classifier = new AlienClassifier();
        Map<String, List<AlienSpecies>> byUniverse = classifier.classifyByUniverse(aliens);
        
        System.out.println("Total Aliens: " + aliens.size() + "\n");
        System.out.println("Classification by Universe:\n");

        int totalClassified = 0;
        
        // Star Wars
        int starWars = 0;
        starWars += byUniverse.getOrDefault("Star Wars (Wookie)", List.of()).size();
        starWars += byUniverse.getOrDefault("Star Wars (Ewok)", List.of()).size();
        if (starWars > 0) {
            System.out.println("STAR WARS UNIVERSE: " + starWars + " aliens");
            if (byUniverse.containsKey("Star Wars (Wookie)")) {
                System.out.println("  - Wookie: " + byUniverse.get("Star Wars (Wookie)").size());
            }
            if (byUniverse.containsKey("Star Wars (Ewok)")) {
                System.out.println("  - Ewok: " + byUniverse.get("Star Wars (Ewok)").size());
            }
            totalClassified += starWars;
        }
        
        // Marvel
        int marvel = byUniverse.getOrDefault("Marvel (Asgardian)", List.of()).size();
        if (marvel > 0) {
            System.out.println("\nMARVEL UNIVERSE: " + marvel + " aliens");
            System.out.println("  - Asgardian: " + marvel);
            totalClassified += marvel;
        }
        
        // Hitchhiker's
        int hitchhiker = 0;
        hitchhiker += byUniverse.getOrDefault("Hitchhiker's (Betelgeusian)", List.of()).size();
        hitchhiker += byUniverse.getOrDefault("Hitchhiker's (Vogon)", List.of()).size();
        if (hitchhiker > 0) {
            System.out.println("\nHITCHHIKER'S UNIVERSE: " + hitchhiker + " aliens");
            if (byUniverse.containsKey("Hitchhiker's (Betelgeusian)")) {
                System.out.println("  - Betelgeusian: " + byUniverse.get("Hitchhiker's (Betelgeusian)").size());
            }
            if (byUniverse.containsKey("Hitchhiker's (Vogon)")) {
                System.out.println("  - Vogon: " + byUniverse.get("Hitchhiker's (Vogon)").size());
            }
            totalClassified += hitchhiker;
        }
        
        // Lord of the Rings
        int lotr = 0;
        lotr += byUniverse.getOrDefault("Lord of the Rings (Elf)", List.of()).size();
        lotr += byUniverse.getOrDefault("Lord of the Rings (Dwarf)", List.of()).size();
        if (lotr > 0) {
            System.out.println("\nLORD OF THE RINGS UNIVERSE: " + lotr + " aliens");
            if (byUniverse.containsKey("Lord of the Rings (Elf)")) {
                System.out.println("  - Elf: " + byUniverse.get("Lord of the Rings (Elf)").size());
            }
            if (byUniverse.containsKey("Lord of the Rings (Dwarf)")) {
                System.out.println("  - Dwarf: " + byUniverse.get("Lord of the Rings (Dwarf)").size());
            }
            totalClassified += lotr;
        }
        
        // Undefined
        int undefined = byUniverse.getOrDefault("Undefined Universe", List.of()).size();
        System.out.println("\nUNDEFINED UNIVERSE: " + undefined + " aliens");
        System.out.println("  (incomplete data - cannot determine universe)");
        
        System.out.println("\n" + "~".repeat(50));
        System.out.println("SUMMARY:");
        System.out.println("  Successfully Classified: " + totalClassified + " aliens");
        System.out.println("  Undefined (incomplete): " + undefined + " aliens");
        System.out.println("  Total: " + aliens.size() + " aliens");
        
        // Write output files using View class
        System.out.println("\n" + "~".repeat(50));
        System.out.println("Writing output files...\n");
        
        try {
            AlienView view = new AlienView();
            
            // Write detailed classification (all aliens with their data)
            view.writeClassificationToJson(byUniverse, "output.json");
            System.out.println("✓ Detailed classification written to: output.json");
            
            // Write summary (just counts for each category)
            view.writeSummaryToJson(byUniverse, "output_summary.json");
            System.out.println("✓ Summary statistics written to: output_summary.json");
            
            System.out.println("\nOutput files created successfully!");
            
        } catch (IOException e) {
            System.err.println("Error writing output files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AlienDataLoader {
    
    /**
     * Loads alien data from a JSON file and converts it to AlienSpecies objects
     * @param filename The path to the JSON file
     * @return A list of AlienSpecies objects
     */
    public static List<AlienSpecies> loadAliens(String filename) {
        List<AlienSpecies> aliens = new ArrayList<>();
        
        try {
            // Read the JSON file
            String content = Files.readString(Paths.get(filename));
            
            // Parse the JSON
            JSONObject root = new JSONObject(content);
            JSONArray aliensArray = root.getJSONArray("aliens");
            
            // Loop through each alien in the JSON array
            for (int i = 0; i < aliensArray.length(); i++) {
                JSONObject alienJson = aliensArray.getJSONObject(i);
                
                // Extract basic fields
                String name = alienJson.getString("name");
                String planet = alienJson.getString("planet");
                String universe = alienJson.getString("universe");
                String classification = alienJson.getString("classification");
                String temperament = alienJson.getString("temperament");
                long population = alienJson.getLong("population");
                
                // Extract abilities array
                JSONArray abilitiesJson = alienJson.getJSONArray("abilities");
                List<String> abilities = new ArrayList<>();
                for (int j = 0; j < abilitiesJson.length(); j++) {
                    abilities.add(abilitiesJson.getString(j));
                }
                
                // Create AlienSpecies object
                AlienSpecies alien = new AlienSpecies(
                    name, 
                    planet, 
                    universe, 
                    classification, 
                    temperament, 
                    population, 
                    abilities
                );
                
                // Add to list
                aliens.add(alien);
            }
            
            System.out.println("Successfully loaded " + aliens.size() + " aliens from " + filename);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
        
        return aliens;
    }
    
    /**
     * Main method for testing the loader
     */
    public static void main(String[] args) {
        List<AlienSpecies> aliens = loadAliens("aliens.json");
        
        // Print all loaded aliens
        System.out.println("\n=== All Loaded Aliens ===");
        for (AlienSpecies alien : aliens) {
            System.out.println(alien);
            System.out.println();
        }
    }
}

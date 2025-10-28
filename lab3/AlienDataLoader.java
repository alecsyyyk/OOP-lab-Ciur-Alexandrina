package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AlienDataLoader {
   
 // reads alien data from a JSON file and turns it into AlienSpecies objects.
 // @param filename Path to the JSON file.
 // @return List of AlienSpecies objects.
 
    public static List<AlienSpecies> loadAliens(String filename) {
        List<AlienSpecies> aliens = new ArrayList<>();
        
        try {
            String content = Files.readString(Paths.get(filename));
            
            JSONObject root = new JSONObject(content);
            JSONArray aliensArray = root.getJSONArray("aliens");
            
            for (int i = 0; i < aliensArray.length(); i++) {
                JSONObject alienJson = aliensArray.getJSONObject(i);
                
                String name = alienJson.getString("name");
                String planet = alienJson.getString("planet");
                String universe = alienJson.getString("universe");
                String classification = alienJson.getString("classification");
                String temperament = alienJson.getString("temperament");
                long population = alienJson.getLong("population");
                               
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
    
        public static void main(String[] args) {
        List<AlienSpecies> aliens = loadAliens("aliens.json");
           System.out.println("\n All Loaded Aliens:");

        for (AlienSpecies alien : aliens) {
            System.out.println(alien);
            System.out.println();
        }
    }
}

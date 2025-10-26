package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

public class AlienJsonParser {
    
    public static void main(String[] args) {
        System.out.println("=== Parsing aliens.json with JSON Library ===\n");
        
        try {
            // Read the file
            String content = Files.readString(Paths.get("aliens.json"));
            
            // Parse as JSON object
            JSONObject root = new JSONObject(content);
            
            // Get the aliens array
            JSONArray aliens = root.getJSONArray("aliens");
            
            System.out.println("Total aliens found: " + aliens.length() + "\n");
            
            // Print each alien separately
            for (int i = 0; i < aliens.length(); i++) {
                JSONObject alien = aliens.getJSONObject(i);
                
                System.out.println("=== Alien #" + (i + 1) + " ===");
                System.out.println("Name: " + alien.getString("name"));
                System.out.println("Planet: " + alien.getString("planet"));
                System.out.println("Universe: " + alien.getString("universe"));
                System.out.println("Classification: " + alien.getString("classification"));
                System.out.println("Temperament: " + alien.getString("temperament"));
                System.out.println("Population: " + alien.getLong("population"));
                
                // Print abilities
                JSONArray abilities = alien.getJSONArray("abilities");
                System.out.println("Abilities:");
                for (int j = 0; j < abilities.length(); j++) {
                    System.out.println("  - " + abilities.getString(j));
                }
                System.out.println();
            }
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

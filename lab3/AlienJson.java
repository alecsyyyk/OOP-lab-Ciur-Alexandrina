package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

public class AlienJson {
    
    public static void main(String[] args) {
        System.out.println("=== Parsing aliens.json with JSON Library ===\n");
        
        try {
            Path filePath = Paths.get("aliens.json");
            String content = Files.readString(filePath);
            
            JSONArray aliens = new JSONArray(content);
            
            System.out.println("Total aliens found: " + aliens.length() + "\n");
            
        
            int displayCount = Math.min(5, aliens.length());
            System.out.println("Displaying first " + displayCount + " aliens:\n");
            
            for (int i = 0; i < displayCount; i++) {
                JSONObject alien = aliens.getJSONObject(i);
                
                System.out.println("Alien #" + (i + 1) + " Details:");
                
                System.out.println("  ID: " + alien.getInt("id"));
              
                if (!alien.isNull("isHumanoid")) {
                    System.out.println("  Is Humanoid: " + alien.getBoolean("isHumanoid"));
                } else {
                    System.out.println("  Is Humanoid: Unknown");
                }
                
                // originPlanet can be null
                if (!alien.isNull("originPlanet")) {
                    System.out.println("  Origin Planet: " + alien.getString("originPlanet"));
                } else {
                    System.out.println("  Origin Planet: Unknown");
                }
                
                // age can be null
                if (!alien.isNull("age")) {
                    System.out.println("  Age: " + alien.getInt("age"));
                } else {
                    System.out.println("  Age: Unknown");
                }
                
                // physicalTraits can be null
                if (!alien.isNull("physicalTraits")) {
                    JSONArray traits = alien.getJSONArray("physicalTraits");
                    System.out.println("  Physical Traits:");
                    for (int j = 0; j < traits.length(); j++) {
                        System.out.println("    - " + traits.getString(j));
                    }
                } else {
                    System.out.println("  Physical Traits: None recorded");
                }
                
                System.out.println(); 
            }
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


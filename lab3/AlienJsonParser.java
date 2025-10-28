package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

public class AlienJsonParser {
    
    public static void main(String[] args) {
        System.out.println(" Parsing aliens.json with JSON Library \n");
        
        try {
           Path filePath = Paths.get("aliens.json");
           String content = Files.readString(filePath);
            
            JSONObject root = new JSONObject(content);
           
            JSONArray aliens = root.getJSONArray("aliens");
            
            System.out.println("Total aliens found: " + aliens.length() + "\n");
            
            for (int i = 0; i < aliens.length(); i++) {
                JSONObject alien = aliens.getJSONObject(i);
                
                System.out.println("Alien #" + (i + 1) + " Details:");
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

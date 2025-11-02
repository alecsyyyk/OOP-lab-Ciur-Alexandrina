package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AlienInfo{
    public static List<AlienSpecies> aliens(String filename) {
        List<AlienSpecies> aliens = new ArrayList<>();
        
        try {
            String content = Files.readString(Paths.get(filename));
            
            JSONArray aliensArray = new JSONArray(content);
            
            for (int i = 0; i < aliensArray.length(); i++) {
                JSONObject alienJson = aliensArray.getJSONObject(i);
 
                int id = alienJson.getInt("id");
         
                Boolean isHumanoid = alienJson.isNull("isHumanoid") ? 
                    null : alienJson.getBoolean("isHumanoid");
      
                String originPlanet = alienJson.isNull("originPlanet") ? 
                    null : alienJson.getString("originPlanet");

                Integer age = alienJson.isNull("age") ? 
                    null : alienJson.getInt("age");
           
                List<String> physicalTraits = null;
                if (!alienJson.isNull("physicalTraits")) {
                    JSONArray traitsJson = alienJson.getJSONArray("physicalTraits");
                    physicalTraits = new ArrayList<>();
                    for (int j = 0; j < traitsJson.length(); j++) {
                        physicalTraits.add(traitsJson.getString(j));
                    }
                }
                
                AlienSpecies alien = new AlienSpecies(
                    id,
                    isHumanoid,
                    originPlanet,
                    age,
                    physicalTraits
                );
                
                aliens.add(alien);
            }
            
            System.out.println("Successfully loaded " + aliens.size() + " aliens from " + filename);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
        
        return aliens;
    }
    
    public static void main(String[] args) {
        List<AlienSpecies> aliens = aliens("aliens.json");
        
        System.out.println("\n Sample Aliens (first 5)");
        for (int i = 0; i < Math.min(5, aliens.size()); i++) {
            System.out.println(aliens.get(i));
            System.out.println();
        }
        
        System.out.println("Total aliens loaded: " + aliens.size());
    }
}
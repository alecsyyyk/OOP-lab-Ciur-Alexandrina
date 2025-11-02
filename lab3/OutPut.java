package lab3;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class OutPut {
    public static JSONObject alienToJson(AlienSpecies alien) {
        JSONObject json = new JSONObject();
        json.put("id", alien.getId());
        json.put("isHumanoid", alien.getIsHumanoid());
        json.put("originPlanet", alien.getOriginPlanet());
        json.put("age", alien.getAge());

        if (alien.hasTraitsData()) {
            JSONArray trait = new JSONArray(alien.getPhysicalTraits());
            json.put("physicalTraits", trait);
        } else {
            json.put("physicalTraits", JSONObject.NULL);
        }
        return json;
    }

    public static void writeAllToJson(Map<String, List<AlienSpecies>> classification, String output) {
        try {
            Files.createDirectories(Paths.get("output"));

            JSONObject root = new JSONObject();

            for (String category : classification.keySet()){
                JSONArray aliensArray = new JSONArray();
                for (AlienSpecies alien : classification.get(category)){
                     aliensArray.put(alienToJson(alien));
                }
                root.put(category, aliensArray);
            }

            FileWriter file = new FileWriter ("output/" + output);
            file.write(root.toString(2));
            file.close();

            System.out.println("It's work" + output);
        }catch(IOException e){
            System.out.println("Error.."+ e.getMessage());
        }
    }

    public static void writeClassification(List<AlienSpecies> aliens) {
        AlienClassifier classifier = new AlienClassifier();
        
        Map<String, List<AlienSpecies>> byPlanet = classifier.classifyByPlanet(aliens);
        writeAllToJson(byPlanet, "by_planet.json");

        Map<String, List<AlienSpecies>> byHumanoid = classifier.classifyByHumanoid(aliens);
        writeAllToJson(byHumanoid, "by_humanoid.json");

        Map<String, List<AlienSpecies>> byAge = classifier.classifyByAge(aliens);
        writeAllToJson(byAge, "by_age.json");
        
        Map<String, List<AlienSpecies>> byTraits = classifier.classifyBySpecificTrait(aliens);
        writeAllToJson(byTraits, "by_traits.json");
    }    

    public static void main(String[] args) {
        List<AlienSpecies> aliens = AlienInfo.aliens("aliens.json");

        writeClassification(aliens);

        System.out.println("\nAll classification you can see in output file");
    }
}
      
   

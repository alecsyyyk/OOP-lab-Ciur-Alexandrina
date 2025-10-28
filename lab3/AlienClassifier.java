package lab3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlienClassifier {

    public Map<String, List<AlienSpecies>> classifyByUniverse(List<AlienSpecies> aliens){
        Map<String, List<AlienSpecies>> classified = new HashMap<>();
        for (AlienSpecies alien : aliens) {
            String universe = alien.getUniverse();

            if (!classified.containsKey(universe)) {
                classified.put(universe, new ArrayList<>());
            }
           
                classified.get(universe).add(alien);
        }
        return classified;
    }


    public Map<String, List<AlienSpecies>> classifyByPopulation(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();

        for (AlienSpecies alien : aliens){
            String category;
            long population = alien.getPopulation();

            if (population == 0){
                category = "Extinct";
            } else if (population < 1_000_000){
                category = "Small";
            } else if (population < 1_000_000_000){
                category = "Medium";
            } else {
                category = "Large";
            }

            if (!classified.containsKey(category)) {
                classified.put(category, new ArrayList<>());
            }

            classified.get(category).add(alien);
        }
        return classified;
    }
    
}

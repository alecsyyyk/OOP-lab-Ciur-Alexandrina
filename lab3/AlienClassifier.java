package lab3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlienClassifier {

    public Map<String, List<AlienSpecies>> classifyByPlanet(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();
        
        for (AlienSpecies alien : aliens) {
            String planet = alien.getOriginPlanet();
            String category = (planet != null) ? planet : "Unknown";

            classified.putIfAbsent(category, new ArrayList<>());
            classified.get(category).add(alien);
        }
        
        return classified;
    }

    public Map<String, List<AlienSpecies>> classifyByHumanoid(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();
        
        for (AlienSpecies alien : aliens) {
            String category;
            
            if (alien.getIsHumanoid() == null) {
                category = "Unknown";
            } else if (alien.getIsHumanoid()) {
                category = "Humanoid";
            } else {
                category = "Non-Humanoid";
            }

            classified.putIfAbsent(category, new ArrayList<>());
            classified.get(category).add(alien);
        }
        
        return classified;
    }

    public Map<String, List<AlienSpecies>> classifyByAge(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();

        for (AlienSpecies alien : aliens) {
            String category;
            
            if (alien.getAge() == null) {
                category = "Unknown Age";
            } else if (alien.getAge() < 100) {
                category = "Young (<100)";
            } else if (alien.getAge() < 1000) {
                category = "Adult (100-1000)";
            } else {
                category = "Ancient (>1000)";
            }

            classified.putIfAbsent(category, new ArrayList<>());
            classified.get(category).add(alien);
        }
        
        return classified;
    }

    public Map<String, List<AlienSpecies>> classifyByTraitsAvailability(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();
        
        for (AlienSpecies alien : aliens) {
            String category = alien.hasTraitsData() ? "Has Traits" : "No Traits";
            
            classified.putIfAbsent(category, new ArrayList<>());
            classified.get(category).add(alien);
        }
        
        return classified;
    }

    public Map<String, List<AlienSpecies>> classifyBySpecificTrait(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();
        
        for (AlienSpecies alien : aliens) {
            if (alien.hasTraitsData()) {
                for (String trait : alien.getPhysicalTraits()) {
                    classified.putIfAbsent(trait, new ArrayList<>());
                    classified.get(trait).add(alien);
                }
            }
        }
        
        return classified;
    }
}

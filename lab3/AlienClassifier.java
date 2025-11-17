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
            String category;
            if (alien.hasTraitsData()) {
                category = "Has Traits";
            } else {
                category = "No Traits";
            }
            
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

    public Map<String, List<AlienSpecies>> classifyByUniverse(List<AlienSpecies> aliens) {
        Map<String, List<AlienSpecies>> classified = new HashMap<>();
        
        for (AlienSpecies alien : aliens) {
            String universe = determineUniverse(alien);
            
            classified.putIfAbsent(universe, new ArrayList<>());
            classified.get(universe).add(alien);
        }
        
        return classified;
    }
    
    private String determineUniverse(AlienSpecies alien) {
        // Star Wars Universe
        if (isWookie(alien)) {
            return "Star Wars (Wookie)";
        }
        if (isEwok(alien)) {
            return "Star Wars (Ewok)";
        }
        
        // Marvel Universe
        if (isAsgardian(alien)) {
            return "Marvel (Asgardian)";
        }
        
        // Hitchhiker's Universe
        if (isBetelgeusian(alien)) {
            return "Hitchhiker's (Betelgeusian)";
        }
        if (isVogon(alien)) {
            return "Hitchhiker's (Vogon)";
        }
        
        // Lord of the Rings Universe
        if (isElf(alien)) {
            return "Lord of the Rings (Elf)";
        }
        if (isDwarf(alien)) {
            return "Lord of the Rings (Dwarf)";
        }
        
        // Undefined Universe
        return "Undefined Universe";
    }
    
    // Star Wars Species
    private boolean isWookie(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && !alien.getIsHumanoid() &&
               "KASHYYYK".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 && alien.getAge() <= 400 &&
               hasTraits(alien, new String[]{"HAIRY", "TALL"});
    }
    
    private boolean isEwok(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && !alien.getIsHumanoid() &&
               "ENDOR".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 && alien.getAge() <= 60 &&
               hasTraits(alien, new String[]{"SHORT", "HAIRY"});
    }
    
    // Marvel Species
    private boolean isAsgardian(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && alien.getIsHumanoid() &&
               "ASGARD".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 && alien.getAge() <= 5000 &&
               hasTraits(alien, new String[]{"BLONDE", "TALL"});
    }
    
    // Hitchhiker's Species
    private boolean isBetelgeusian(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && alien.getIsHumanoid() &&
               "BETELGEUSE".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 && alien.getAge() <= 100 &&
               hasTraits(alien, new String[]{"EXTRA_ARMS", "EXTRA_HEAD"});
    }
    
    private boolean isVogon(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && !alien.getIsHumanoid() &&
               "VOGSPHERE".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 && alien.getAge() <= 200 &&
               hasTraits(alien, new String[]{"GREEN", "BULKY"});
    }
    
    // Lord of the Rings Species
    private boolean isElf(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && alien.getIsHumanoid() &&
               "EARTH".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 &&
               hasTraits(alien, new String[]{"BLONDE", "POINTY_EARS"});
    }
    
    private boolean isDwarf(AlienSpecies alien) {
        return alien.getIsHumanoid() != null && alien.getIsHumanoid() &&
               "EARTH".equals(alien.getOriginPlanet()) &&
               alien.getAge() != null && alien.getAge() >= 0 && alien.getAge() <= 200 &&
               hasTraits(alien, new String[]{"SHORT", "BULKY"});
    }
    
    // Helper method to check if alien has all required traits
    private boolean hasTraits(AlienSpecies alien, String[] requiredTraits) {
        if (!alien.hasTraitsData()) {
            return false;
        }
        
        List<String> traits = alien.getPhysicalTraits();
        for (String required : requiredTraits) {
            if (!traits.contains(required)) {
                return false;
            }
        }
        return true;
    }
}

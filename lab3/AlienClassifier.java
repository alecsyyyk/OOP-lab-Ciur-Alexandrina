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
            String category;
            
            if (planet != null) {
                category = planet;
            } else {
                category = "Unknown";
            }

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
        
        // Lord of the Rings Universe - check Dwarf first (more specific)
        if (isDwarf(alien)) {
            return "Lord of the Rings (Dwarf)";
        }
        if (isElf(alien)) {
            return "Lord of the Rings (Elf)";
        }
        
        // Undefined Universe
        return "Undefined Universe";
    }
    
    // Star Wars Species
    private boolean isWookie(AlienSpecies alien) {
        // Must have planet KASHYYYK
        if (!"KASHYYYK".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be false
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != false) {
            return false;
        }
        
        // If age present, must be in range
        if (alien.hasAgeData() && (alien.getAge() < 0 || alien.getAge() > 400)) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"HAIRY", "TALL"})) {
            return false;
        }
        
        return true;
    }
    
    private boolean isEwok(AlienSpecies alien) {
        // Must have planet ENDOR
        if (!"ENDOR".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be false
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != false) {
            return false;
        }
        
        // If age present, must be in range
        if (alien.hasAgeData() && (alien.getAge() < 0 || alien.getAge() > 60)) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"SHORT", "HAIRY"})) {
            return false;
        }
        
        return true;
    }
    
    // Marvel Species
    private boolean isAsgardian(AlienSpecies alien) {
        // Must have planet ASGARD
        if (!"ASGARD".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be true
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != true) {
            return false;
        }
        
        // If age present, must be in range
        if (alien.hasAgeData() && (alien.getAge() < 0 || alien.getAge() > 5000)) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"BLONDE", "TALL"})) {
            return false;
        }
        
        return true;
    }
    
    // Hitchhiker's Species
    private boolean isBetelgeusian(AlienSpecies alien) {
        // Must have planet BETELGEUSE
        if (!"BETELGEUSE".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be true
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != true) {
            return false;
        }
        
        // If age present, must be in range
        if (alien.hasAgeData() && (alien.getAge() < 0 || alien.getAge() > 100)) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"EXTRA_ARMS", "EXTRA_HEAD"})) {
            return false;
        }
        
        return true;
    }
    
    private boolean isVogon(AlienSpecies alien) {
        // Must have planet VOGSPHERE
        if (!"VOGSPHERE".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be false
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != false) {
            return false;
        }
        
        // If age present, must be in range
        if (alien.hasAgeData() && (alien.getAge() < 0 || alien.getAge() > 200)) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"GREEN", "BULKY"})) {
            return false;
        }
        
        return true;
    }
    
    // Lord of the Rings Species  
    private boolean isElf(AlienSpecies alien) {
        // Must have planet EARTH
        if (!"EARTH".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be true
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != true) {
            return false;
        }
        
        // If age present, must be >= 0 (no upper limit)
        if (alien.hasAgeData() && alien.getAge() < 0) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"BLONDE", "POINTY_EARS"})) {
            return false;
        }
        
        return true;
    }
    
    private boolean isDwarf(AlienSpecies alien) {
        // Must have planet EARTH
        if (!"EARTH".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // If isHumanoid data present, must be true
        if (alien.hasHumanoidData() && alien.getIsHumanoid() != true) {
            return false;
        }
        
        // If age present, must be in range
        if (alien.hasAgeData() && (alien.getAge() < 0 || alien.getAge() > 200)) {
            return false;
        }
        
        // If traits present, must contain required traits
        if (alien.hasTraitsData() && !hasTraits(alien, new String[]{"SHORT", "BULKY"})) {
            return false;
        }
        
        return true;
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
    
    // Helper method to check if alien has EXACTLY the required traits (no more, no less)
    private boolean hasExactTraits(AlienSpecies alien, String[] requiredTraits) {
        if (!alien.hasTraitsData()) {
            return false;
        }
        
        List<String> traits = alien.getPhysicalTraits();
        
        // Must have exactly the same number of traits
        if (traits.size() != requiredTraits.length) {
            return false;
        }
        
        // Must contain all required traits
        for (String required : requiredTraits) {
            if (!traits.contains(required)) {
                return false;
            }
        }
        
        return true;
    }
}

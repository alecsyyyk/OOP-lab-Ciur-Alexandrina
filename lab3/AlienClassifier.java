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
        
        if (isWookie(alien)) {
            return "Star Wars (Wookie)";
        }
        if (isEwok(alien)) {
            return "Star Wars (Ewok)";
        }
        
        if (isAsgardian(alien)) {
            return "Marvel (Asgardian)";
        }

        if (isBetelgeusian(alien)) {
            return "Hitchhiker's (Betelgeusian)";
        }
        if (isVogon(alien)) {
            return "Hitchhiker's (Vogon)";
        }
        
        if (isDwarf(alien)) {
            return "Lord of the Rings (Dwarf)";
        }
        if (isElf(alien)) {
            return "Lord of the Rings (Elf)";
        }
        
        // Fallback: classify by traits if no planet data
        if (!alien.hasPlanetData() && alien.hasTraitsData()) {
            String traitBasedUniverse = classifyByTraitsOnly(alien);
            if (traitBasedUniverse != null) {
                return traitBasedUniverse;
            }
        }
        
        return "Undefined Universe";
    }
    
    // Classify aliens without planet data based on their traits
    private String classifyByTraitsOnly(AlienSpecies alien) {
        List<String> traits = alien.getPhysicalTraits();
        
        // Check for Dwarf traits (SHORT + BULKY)
        if (traits.contains("SHORT") && traits.contains("BULKY")) {
            if (!alien.hasAgeData() || (alien.getAge() >= 0 && alien.getAge() <= 200)) {
                if (!alien.hasHumanoidData() || alien.getIsHumanoid()) {
                    return "Lord of the Rings (Dwarf)";
                }
            }
        }
        
        // Check for Elf traits (POINTY_EARS)
        if (traits.contains("POINTY_EARS")) {
            if (!alien.hasAgeData() || alien.getAge() >= 0) {
                if (!alien.hasHumanoidData() || alien.getIsHumanoid()) {
                    return "Lord of the Rings (Elf)";
                }
            }
        }
        
        // Check for Asgardian traits (BLONDE + TALL)
        if (traits.contains("BLONDE") && traits.contains("TALL")) {
            if (!alien.hasAgeData() || (alien.getAge() >= 0 && alien.getAge() <= 5000)) {
                if (!alien.hasHumanoidData() || alien.getIsHumanoid()) {
                    return "Marvel (Asgardian)";
                }
            }
        }
        
        // Check for Wookie traits (HAIRY + TALL)
        if (traits.contains("HAIRY") && traits.contains("TALL")) {
            if (!alien.hasAgeData() || (alien.getAge() >= 0 && alien.getAge() <= 400)) {
                if (!alien.hasHumanoidData() || !alien.getIsHumanoid()) {
                    return "Star Wars (Wookie)";
                }
            }
        }
        
        // Check for Ewok traits (SHORT + HAIRY)
        if (traits.contains("SHORT") && traits.contains("HAIRY")) {
            if (!alien.hasAgeData() || (alien.getAge() >= 0 && alien.getAge() <= 60)) {
                if (!alien.hasHumanoidData() || !alien.getIsHumanoid()) {
                    return "Star Wars (Ewok)";
                }
            }
        }
        
        // Check for Betelgeusian traits (EXTRA_ARMS + EXTRA_HEAD)
        if (traits.contains("EXTRA_ARMS") && traits.contains("EXTRA_HEAD")) {
            if (!alien.hasAgeData() || (alien.getAge() >= 0 && alien.getAge() <= 100)) {
                if (!alien.hasHumanoidData() || alien.getIsHumanoid()) {
                    return "Hitchhiker's (Betelgeusian)";
                }
            }
        }
        
        // Check for Vogon traits (GREEN + BULKY)
        if (traits.contains("GREEN") && traits.contains("BULKY")) {
            if (!alien.hasAgeData() || (alien.getAge() >= 0 && alien.getAge() <= 200)) {
                if (!alien.hasHumanoidData() || !alien.getIsHumanoid()) {
                    return "Hitchhiker's (Vogon)";
                }
            }
        }
        
        return null; // Cannot determine from traits alone
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
        
        // Traits are optional - don't require them
        
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
        
        // Traits are optional - don't require them
        
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
        
        // Traits are optional - don't require them
        
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
        
        // Traits are optional - don't require them
        
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
        
        // Traits are optional - don't require them
        
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
        
        // Traits are optional for Elves - don't require specific traits
        // (This allows more Earth humanoids to be classified as Elves)
        
        return true;
    }
    
    private boolean isDwarf(AlienSpecies alien) {
        // Must have planet EARTH
        if (!"EARTH".equals(alien.getOriginPlanet())) {
            return false;
        }
        
        // Must have traits data with SHORT and BULKY to be classified as Dwarf
        if (!alien.hasTraitsData() || !hasTraits(alien, new String[]{"SHORT", "BULKY"})) {
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

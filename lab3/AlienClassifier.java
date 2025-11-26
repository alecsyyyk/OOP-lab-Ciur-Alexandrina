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
        // Priority rules for Lord of the Rings
        if (alien.hasAgeData() && alien.getAge() > 5000) {
            return "Lord of the Rings (Elf)";
        }
        
        if (hasTrait(alien, "SHORT") && hasTrait(alien, "BULKY")) {
            return "Lord of the Rings (Dwarf)";
        }
        
        // Check for insufficient data cases first
        if (!alien.hasHumanoidData() && !alien.hasPlanetData() && !alien.hasAgeData() && !alien.hasTraitsData()) {
            return "Undefined Universe";
        }
        
        if (!alien.hasHumanoidData() && !alien.hasPlanetData() && alien.hasAgeData() && 
            alien.getAge() < 5000 && !alien.hasTraitsData()) {
            return "Undefined Universe";
        }
        
        if (alien.hasHumanoidData() && !alien.hasPlanetData() && !alien.hasAgeData() && !alien.hasTraitsData()) {
            return "Undefined Universe";
        }

        
        if (alien.hasHumanoidData() && alien.getIsHumanoid()) {
            return classifyHumanoid(alien);
        } else if (alien.hasHumanoidData() && !alien.getIsHumanoid()) {
            return classifyNonHumanoid(alien);
        } else {
            // isHumanoid is null
            return classifyHumanoidUnknown(alien);
        }
    }

    private String classifyHumanoid(AlienSpecies alien) {
        String planet = alien.getOriginPlanet();
        
        if ("ASGARD".equals(planet)) {
            return "Marvel (Asgardian)";
        } else if ("BETELGEUSE".equals(planet)) {
            return "Hitchhiker's (Betelgeusian)";
        } else if ("EARTH".equals(planet)) {
            return "Lord of the Rings (Elf)";
        } else {
            // planet is null, humanoid is true
            if (alien.hasAgeData() && alien.getAge() > 5000) {
                return "Lord of the Rings (Elf)";
            }
            
            if (alien.hasAgeData() && alien.getAge() < 5000) {
                if (hasTrait(alien, "BLONDE") && hasTrait(alien, "TALL")) {
                    return "Marvel (Asgardian)";
                } else if (hasTrait(alien, "BLONDE")) {
                    return "Undefined Universe";
                } else if (alien.getAge() > 400 && hasTrait(alien, "TALL")) {
                    return "Marvel (Asgardian)";
                }
            }
            
            if (alien.hasAgeData() && alien.getAge() < 100) {
                if (hasTrait(alien, "EXTRA_ARMS") || hasTrait(alien, "EXTRA_HEAD")) {
                    return "Hitchhiker's (Betelgeusian)";
                }
            }
            
            if (alien.hasAgeData() && alien.getAge() < 200) {
                if (alien.getAge() > 60 && hasTrait(alien, "SHORT")) {
                    return "Lord of the Rings (Dwarf)";
                }
                if (hasTrait(alien, "SHORT") && hasTrait(alien, "BULKY")) {
                    return "Lord of the Rings (Dwarf)";
                }
            }
            
            if (alien.hasAgeData()) {
                if (hasTrait(alien, "POINTY_EARS")) {
                    return "Lord of the Rings (Elf)";
                }
                if (alien.getAge() > 5000 && hasTrait(alien, "BLONDE")) {
                    return "Lord of the Rings (Elf)";
                }
            }
            
            if (alien.hasAgeData() && !alien.hasTraitsData()) {
                return "Undefined Universe";
            } else if (!alien.hasAgeData()) {
                // age is null
                if (hasTrait(alien, "BLONDE") && hasTrait(alien, "TALL")) {
                    return "Marvel (Asgardian)";
                }
                if (hasTrait(alien, "TALL")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "BLONDE")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "EXTRA_ARMS") || hasTrait(alien, "EXTRA_HEAD")) {
                    return "Hitchhiker's (Betelgeusian)";
                }
                if (hasTrait(alien, "SHORT")) {
                    return "Lord of the Rings (Dwarf)";
                }
                if (hasTrait(alien, "BULKY")) {
                    return "Lord of the Rings (Dwarf)";
                }
                if (hasTrait(alien, "POINTY_EARS")) {
                    return "Lord of the Rings (Elf)";
                }
            }
        }
        
        return "Undefined Universe";
    }

    private String classifyNonHumanoid(AlienSpecies alien) {
        String planet = alien.getOriginPlanet();
        
        if ("KASHYYYK".equals(planet) || "ENDOR".equals(planet)) {
            return "Star Wars (Wookie)";
        }
        if ("VOGSPHERE".equals(planet)) {
            return "Hitchhiker's (Vogon)";
        } else {
            // planet is null, humanoid is false
            if (alien.hasAgeData() && alien.getAge() < 400) {
                if (alien.hasAgeData() && alien.getAge() < 200) {
                    if (hasTrait(alien, "GREEN")) {
                        return "Hitchhiker's (Vogon)";
                    }
                    if (hasTrait(alien, "BULKY")) {
                        return "Hitchhiker's (Vogon)";
                    }
                }
                if (hasTrait(alien, "HAIRY")) {
                    return "Star Wars (Wookie)";
                }
                if (hasTrait(alien, "TALL")) {
                    return "Star Wars (Wookie)";
                }
            } else if (alien.hasAgeData() && alien.getAge() < 60 && 
                       (hasTrait(alien, "HAIRY") || (hasTrait(alien, "SHORT") && !hasTrait(alien, "BULKY")))) {
                return "Star Wars (Ewok)";
            } else {
                if (hasTrait(alien, "HAIRY")) {
                    return "Star Wars (Wookie)";
                }
                if (hasTrait(alien, "TALL")) {
                    return "Star Wars (Wookie)";
                }
                if (alien.hasAgeData() && alien.getAge() < 60 && 
                    (hasTrait(alien, "HAIRY") || (hasTrait(alien, "SHORT") && !hasTrait(alien, "BULKY")))) {
                    return "Star Wars (Ewok)";
                }
                if (hasTrait(alien, "GREEN")) {
                    return "Hitchhiker's (Vogon)";
                }
                if (hasTrait(alien, "BULKY")) {
                    return "Undefined Universe";
                }
            }
        }
        
        return "Undefined Universe";
    }

    private String classifyHumanoidUnknown(AlienSpecies alien) {
        String planet = alien.getOriginPlanet();
        
        if ("ASGARD".equals(planet)) {
            return "Marvel (Asgardian)";
        } else if ("BETELGEUSE".equals(planet)) {
            return "Hitchhiker's (Betelgeusian)";
        } else if ("EARTH".equals(planet)) {
            return "Lord of the Rings (Elf)";
        } else if ("KASHYYYK".equals(planet) || "ENDOR".equals(planet)) {
            return "Star Wars (Wookie)";
        } else if ("VOGSPHERE".equals(planet)) {
            return "Hitchhiker's (Vogon)";
        } else {
            // planet is null, humanoid is null
            if (alien.hasAgeData() && alien.getAge() > 5000) {
                return "Lord of the Rings (Elf)";
            } else if (alien.hasAgeData() && alien.getAge() < 5000 && 
                       hasTrait(alien, "POINTY_EARS") && hasTrait(alien, "BLONDE")) {
                return "Lord of the Rings (Elf)";
            } else if (alien.hasAgeData() && alien.getAge() < 400) {
                if (hasTrait(alien, "HAIRY")) {
                    return "Star Wars (Wookie)";
                }
                if (hasTrait(alien, "TALL")) {
                    return "Undefined Universe";
                }
                
                if (alien.hasAgeData() && alien.getAge() < 200) {
                    if (hasTrait(alien, "BULKY") && hasTrait(alien, "SHORT")) {
                        return "Lord of the Rings (Dwarf)";
                    } else if (alien.getAge() < 60 && hasTrait(alien, "SHORT")) {
                        return "Undefined Universe";
                    } else if (hasTrait(alien, "SHORT") && alien.getAge() > 60) {
                        return "Lord of the Rings (Dwarf)";
                    } else if (hasTrait(alien, "GREEN")) {
                        return "Hitchhiker's (Vogon)";
                    }
                }
            } else if (alien.hasAgeData() && alien.getAge() < 60 && 
                       (hasTrait(alien, "HAIRY") || (hasTrait(alien, "SHORT") && !hasTrait(alien, "BULKY")))) {
                return "Star Wars (Ewok)";
            } else if (alien.hasAgeData() && alien.getAge() < 5000) {
                if (hasTrait(alien, "BLONDE") && hasTrait(alien, "TALL")) {
                    return "Marvel (Asgardian)";
                } else if (hasTrait(alien, "BLONDE")) {
                    return "Undefined Universe";
                } else if (alien.getAge() > 400 && hasTrait(alien, "TALL")) {
                    return "Marvel (Asgardian)";
                }
            } else if (alien.hasAgeData() && alien.getAge() < 100) {
                if (hasTrait(alien, "EXTRA_ARMS") || hasTrait(alien, "EXTRA_HEAD")) {
                    return "Hitchhiker's (Betelgeusian)";
                }
            } else if (alien.hasAgeData() && alien.getAge() < 200) {
                if (hasTrait(alien, "GREEN")) {
                    return "Hitchhiker's (Vogon)";
                }
                if (hasTrait(alien, "GREEN") && hasTrait(alien, "BULKY")) {
                    return "Hitchhiker's (Vogon)";
                }
                if (hasTrait(alien, "BULKY")) {
                    return "Undefined Universe";
                }
            }
            
            if (alien.hasAgeData()) {
                if (hasTrait(alien, "POINTY_EARS")) {
                    return "Lord of the Rings (Elf)";
                }
                if (alien.getAge() > 5000 && hasTrait(alien, "BLONDE")) {
                    return "Lord of the Rings (Elf)";
                }
                if (hasTrait(alien, "BLONDE") && hasTrait(alien, "POINTY_EARS")) {
                    return "Lord of the Rings (Elf)";
                }
            } else {
                // age is null
                if (hasTrait(alien, "HAIRY")) {
                    return "Star Wars (Wookie)";
                }
                if (hasTrait(alien, "TALL")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "HAIRY") || (hasTrait(alien, "SHORT") && !hasTrait(alien, "BULKY"))) {
                    return "Star Wars (Wookie)";
                }
                if (hasTrait(alien, "BLONDE") && hasTrait(alien, "TALL")) {
                    return "Marvel (Asgardian)";
                }
                if (hasTrait(alien, "TALL")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "BLONDE")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "EXTRA_ARMS") || hasTrait(alien, "EXTRA_HEAD")) {
                    return "Hitchhiker's (Betelgeusian)";
                }
                if (hasTrait(alien, "GREEN")) {
                    return "Hitchhiker's (Vogon)";
                }
                if (hasTrait(alien, "GREEN") && hasTrait(alien, "BULKY")) {
                    return "Hitchhiker's (Vogon)";
                }
                if (hasTrait(alien, "BULKY")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "SHORT")) {
                    return "Undefined Universe";
                }
                if (hasTrait(alien, "SHORT") && hasTrait(alien, "BULKY")) {
                    return "Lord of the Rings (Dwarf)";
                }
                if (hasTrait(alien, "POINTY_EARS")) {
                    return "Lord of the Rings (Elf)";
                }
            }
        }
        
        return "Undefined Universe";
    }

    private boolean hasTrait(AlienSpecies alien, String trait) {
        if (!alien.hasTraitsData()) {
            return false;
        }
        return alien.getPhysicalTraits().contains(trait);
    }
}
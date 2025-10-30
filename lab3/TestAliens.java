package lab3;
import java.util.Arrays;
import java.util.List;

/**
 * Tests AlienSpecies with NEW data structure
 * Creates 3 test aliens manually with new constructor:
 * AlienSpecies(int id, Boolean isHumanoid, String originPlanet, Integer age, List<String> physicalTraits)
 */
public class TestAliens {
    public static void main(String[] args) {
        System.out.println("Testing AlienSpecies with New Data Structure \n");
        
        // Create test alien 1: Klingon-like (humanoid from VOGSPHERE)
        List<String> traits1 = Arrays.asList("BULKY", "HAIRY", "TALL");
        AlienSpecies alien1 = new AlienSpecies(
            1001,           // id
            true,           // isHumanoid
            "VOGSPHERE",    // originPlanet
            150,            // age
            traits1         // physicalTraits
        );
        
        // Create test alien 2: Wookiee-like (humanoid from KASHYYYK)
        List<String> traits2 = Arrays.asList("HAIRY", "TALL", "BULKY");
        AlienSpecies alien2 = new AlienSpecies(
            1002,           // id
            true,           // isHumanoid
            "KASHYYYK",     // originPlanet
            250,            // age
            traits2         // physicalTraits
        );
        
        // Create test alien 3: Unknown species with incomplete data
        AlienSpecies alien3 = new AlienSpecies(
            1003,           // id
            null,           // isHumanoid - unknown
            "BETELGEUSE",   // originPlanet
            null,           // age - unknown
            Arrays.asList("GREEN", "EXTRA_ARMS")  // physicalTraits
        );
        
        // Print all aliens using toString()
        System.out.println(alien1);
        System.out.println("\n" + "=".repeat(50) + "\n");
        System.out.println(alien2);
        System.out.println("\n" + "=".repeat(50) + "\n");
        System.out.println(alien3);
        
        // Test NEW getters
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Testing Getters (alien1) ===");
        System.out.println("ID: " + alien1.getId());
        System.out.println("Is Humanoid: " + alien1.getIsHumanoid());
        System.out.println("Origin Planet: " + alien1.getOriginPlanet());
        System.out.println("Age: " + alien1.getAge());
        System.out.println("Physical Traits: " + alien1.getPhysicalTraits());
        
        // Test helper methods
        System.out.println("\n=== Testing Helper Methods (alien3 with null data) ===");
        System.out.println("Has humanoid data? " + alien3.hasHumanoidData());
        System.out.println("Has planet data? " + alien3.hasPlanetData());
        System.out.println("Has age data? " + alien3.hasAgeData());
        System.out.println("Has traits data? " + alien3.hasTraitsData());
    }
}

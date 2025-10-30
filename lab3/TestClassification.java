package lab3;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestClassification {
    public static void main(String[] args) {
        System.out.println("Testing Classification with New Data \n");

        List<AlienSpecies> allAliens = AlienInfo.aliens("aliens.json");
        List<AlienSpecies> testAliens = allAliens.subList(0, Math.min(10, allAliens.size()));
        
        System.out.println("\nTest sample size: " + testAliens.size() + " aliens\n");

        AlienClassifier classifier = new AlienClassifier();
        
        System.out.println("Classification by Planet");
        Map<String, List<AlienSpecies>> byPlanet = classifier.classifyByPlanet(testAliens);
        for (String planet : byPlanet.keySet()) {
            System.out.println(planet + ": " + byPlanet.get(planet).size() + " aliens");
        }
        
        System.out.println("\n Classification by Humanoid Status");
        Map<String, List<AlienSpecies>> byHumanoid = classifier.classifyByHumanoid(testAliens);
        for (String status : byHumanoid.keySet()) {
            System.out.println(status + ": " + byHumanoid.get(status).size() + " aliens");
        }
        
        System.out.println("\n Classification by Age Range ");
        Map<String, List<AlienSpecies>> byAge = classifier.classifyByAge(testAliens);
        for (String ageRange : byAge.keySet()) {
            System.out.println(ageRange + ": " + byAge.get(ageRange).size() + " aliens");
        }
        
        System.out.println("\n Classification by Physical Traits ");
        Map<String, List<AlienSpecies>> byTrait = classifier.classifyBySpecificTrait(testAliens);
        for (String trait : byTrait.keySet()) {
            System.out.println(trait + ": " + byTrait.get(trait).size() + " aliens");
        }
    }
}

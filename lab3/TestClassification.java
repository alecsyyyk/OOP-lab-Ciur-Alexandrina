package lab3;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestClassification {
    public static void main(String[] args) {
        System.out.println("Testing Classification: ");

        List<String> klingonAbilities = Arrays.asList("Enhanced strength", "Warrior culture");
        AlienSpecies alien1 = new AlienSpecies("Klingon", "Soluna", "Star Trek", 
                                               "Humanoid", "Aggressive", 8500000000L, klingonAbilities);
        
        List<String> wookieeAbilities = Arrays.asList("Great strength", "Expert mechanics");
        AlienSpecies alien2 = new AlienSpecies("Wookiee", "Kashyyyk", "Star Wars", 
                                               "Mammalian", "Loyal", 56000000L, wookieeAbilities);
        
        List<AlienSpecies> testAliens = Arrays.asList(alien1, alien2);

         AlienClassifier classifier = new AlienClassifier();
        
         System.out.println("Classify by Universe:");
        Map<String, List<AlienSpecies>> byUniverse = classifier.classifyByUniverse(testAliens);
        
        for (String universe : byUniverse.keySet()) {
            System.out.println("\n" + universe + ":");
            for (AlienSpecies alien : byUniverse.get(universe)) {
                System.out.println("  - " + alien.getName());
            }
        } 
        
    }
}

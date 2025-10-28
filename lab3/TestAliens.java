package lab3;
import java.util.Arrays;
import java.util.List;

public class TestAliens {
    public static void main(String[] args) {
        System.out.println("Testing AlienSpecies\n");
        
        List<String> klingonAbilities = Arrays.asList("Enhanced strength", "Warrior culture", "Honor-based society");
        AlienSpecies alien1 = new AlienSpecies("Klingon", "Soluna", "Star Trek", "Humanoid", "Aggressive", 8500000000L, klingonAbilities);
        
        List<String> wookieeAbilities = Arrays.asList("Great strength", "Expert mechanics", "Long lifespan");
        AlienSpecies alien2 = new AlienSpecies("Wookiee", "Kashyyyk", "Star Wars", "Mammalian", "Loyal", 56000000L, wookieeAbilities);
        
        List<String> kryptonianAbilities = Arrays.asList("Super strength", "Flight", "Heat vision", "Invulnerability");
        AlienSpecies alien3 = new AlienSpecies("Kryptonian", "Krypton", "DC Comics", "Humanoid", "Noble", 0L, kryptonianAbilities);
        
        System.out.println(alien1);
        System.out.println();
        System.out.println(alien2);
        System.out.println();
        System.out.println(alien3);
        
        System.out.println("\n Testing Getters ");
        System.out.println("Name: " + alien1.getName());
        System.out.println("Planet: " + alien1.getPlanet());
        System.out.println("Universe: " + alien1.getUniverse());
        System.out.println("Classification: " + alien1.getClassification());
        System.out.println("Temperament: " + alien1.getTemperament());
        System.out.println("Population: " + alien1.getPopulation());
        System.out.println("Abilities: " + alien1.getAbilities());
    }
}

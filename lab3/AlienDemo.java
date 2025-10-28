package lab3;
import java.util.List;

public class AlienDemo {
    public static void main(String[] args){
        List<AlienSpecies> aliens = AlienDataLoader.loadAliens("aliens.json");

    System.out.println("\n All Alien Names: ");
    for (AlienSpecies alien : aliens) {
        System.out.println(alien.getName());
    }

    System.out.println("\n Aliens with Population > 1 Billion: ");
    for (AlienSpecies alien : aliens) {
    if (alien.getPopulation() > 1_000_000_000) {
        System.out.println(alien.getName() + ": " + alien.getPopulation());
    }
    }
    System.out.println("\n Aliens with More Than 3 Abilities: ");
    for (AlienSpecies alien : aliens) {
    if (alien.getAbilities().size() > 3) {
        System.out.println(alien.getName() + ": " + alien.getAbilities().size() + " abilities");
    }
    }
    System.out.println("\n Humanoid Aliens: ");
    for (AlienSpecies alien : aliens) {
    if (alien.getClassification().equals("Humanoid")) {
        System.out.println(alien.getName() + " - " + alien.getTemperament());
    }
    }
    System.out.println("\n First Ability of Each Alien: ");
    for (AlienSpecies alien : aliens) {
    if (!alien.getAbilities().isEmpty()) {
        System.out.println(alien.getName() + ": " + alien.getAbilities().get(0));
    }
}
    }
}

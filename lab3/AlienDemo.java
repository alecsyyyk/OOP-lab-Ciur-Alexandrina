package lab3;
import java.util.List;

public class AlienDemo {
    public static void main(String[] args) {
        List<AlienSpecies> aliens = AlienInfo.aliens("aliens.json");

        System.out.println("\n All Alien IDs");
        for (AlienSpecies alien : aliens) {
            System.out.println("ID: " + alien.getId());
        }

        System.out.println("\n Aliens from EARTH ");
        for (AlienSpecies alien : aliens) {
            if ("EARTH".equals(alien.getOriginPlanet())) {
                System.out.println("ID " + alien.getId() + " - Planet: " + alien.getOriginPlanet());
            }
        }

        System.out.println("\n Confirmed Humanoid Aliens");
        for (AlienSpecies alien : aliens) {
            if (alien.getIsHumanoid() != null && alien.getIsHumanoid()) {
                System.out.println("ID " + alien.getId() + " from " + 
                    (alien.getOriginPlanet() != null ? alien.getOriginPlanet() : "Unknown"));
            }
        }

        System.out.println("\n Aliens Older Than 1000 Years ");
        for (AlienSpecies alien : aliens) {
            if (alien.getAge() != null && alien.getAge() > 1000) {
                System.out.println("ID " + alien.getId() + " - Age: " + alien.getAge());
            }
        }

        System.out.println("\n Aliens with Multiple Physical Traits");
        for (AlienSpecies alien : aliens) {
            if (alien.hasTraitsData() && alien.getPhysicalTraits().size() > 1) {
                System.out.println("ID " + alien.getId() + " - Traits: " + 
                    String.join(", ", alien.getPhysicalTraits()));
            }
        }

        System.out.println("\n Aliens with HAIRY Trait ");
        for (AlienSpecies alien : aliens) {
            if (alien.hasTraitsData() && alien.getPhysicalTraits().contains("HAIRY")) {
                System.out.println("ID " + alien.getId() + " - " + 
                    String.join(", ", alien.getPhysicalTraits()));
            }
        }

        System.out.println("\n Aliens with Complete Data");
        for (AlienSpecies alien : aliens) {
            if (alien.hasHumanoidData() && alien.hasPlanetData() && 
                alien.hasAgeData() && alien.hasTraitsData()) {
                System.out.println("ID " + alien.getId() + " - Complete profile");
            }
        }

        System.out.println("\n Summary Statistics ");
        long totalAliens = aliens.size();
        long humanoidsConfirmed = aliens.stream()
            .filter(a -> a.getIsHumanoid() != null && a.getIsHumanoid())
            .count();
        long withPlanet = aliens.stream()
            .filter(AlienSpecies::hasPlanetData)
            .count();
        long withAge = aliens.stream()
            .filter(AlienSpecies::hasAgeData)
            .count();

        System.out.println("Total aliens: " + totalAliens);
        System.out.println("Confirmed humanoids: " + humanoidsConfirmed);
        System.out.println("With known planet: " + withPlanet);
        System.out.println("With known age: " + withAge);
    }
}

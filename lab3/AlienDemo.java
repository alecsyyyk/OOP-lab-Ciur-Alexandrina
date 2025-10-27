public class AlienDemo {
    System.out.println("\n === All Alien Names ===");
    for (AlienSpecies alien : aliens) {
        System.out.println(alien.getName());
    }
    
    System.out.println("\n=== Star Trek Aliens Only ===");
    for (AlienSpecies alien : aliens) {
        if (alien.getUniverse().equals("Star Trek")) {
            System.out.println(alien.getName() + " from " + alien.getPlanet());
        }
    }
    System.out.println("\n=== Aliens with Odd Population ===");
    for (AlienSpecies alien : aliens) {
    if (alien.getPopulation() % 2 != 0) {
        System.out.println(alien.getName() + ": " + alien.getPopulation());
    }
    System.out.println("\n=== Alien Count by Universe ===");

    int starTrekCount = 0;
    int starWarsCount = 0;
    int dcCount = 0;
    int otherCount = 0;

    for (AlienSpecies alien : aliens) {
    switch (alien.getUniverse()) {
        case "Star Trek": starTrekCount++; break;
        case "Star Wars": starWarsCount++; break;
        case "DC Comics": dcCount++; break;
        default: otherCount++; break;
    }
 } 
System.out.println("Star Trek: " + starTrekCount);
System.out.println("Star Wars: " + starWarsCount);
System.out.println("DC Comics: " + dcCount);
System.out.println("Other: " + otherCount);
}
System.out.println("\n=== Aliens with Population > 1 Billion ===");
for (AlienSpecies alien : aliens) {
    if (alien.getPopulation() > 1_000_000_000) {
        System.out.println(alien.getName() + ": " + alien.getPopulation());
    }
}
System.out.println("\n=== Aliens with More Than 3 Abilities ===");
for (AlienSpecies alien : aliens) {
    if (alien.getAbilities().size() > 3) {
        System.out.println(alien.getName() + ": " + alien.getAbilities().size() + " abilities");
    }
}
System.out.println("\n=== Humanoid Aliens ===");
for (AlienSpecies alien : aliens) {
    if (alien.getClassification().equals("Humanoid")) {
        System.out.println(alien.getName() + " - " + alien.getTemperament());
    }
}
System.out.println("\n=== First Ability of Each Alien ===");
for (AlienSpecies alien : aliens) {
    if (!alien.getAbilities().isEmpty()) {
        System.out.println(alien.getName() + ": " + alien.getAbilities().get(0));
    }
}
System.out.println("\n=== Search for Klingon ===");
String searchName = "Klingon";
for (AlienSpecies alien : aliens) {
    if (alien.getName().equals(searchName)) {
        System.out.println("Found: " + alien);
        break;
    }
}

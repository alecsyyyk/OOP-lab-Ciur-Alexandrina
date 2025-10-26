public class TestAliens {
    public static void main(String[] args) {
        System.out.println("=== Testing AlienSpecies ===\n");
        
        AlienSpecies alien1 = new AlienSpecies("Klingon", "Qo'noS", "Star Trek");
        AlienSpecies alien2 = new AlienSpecies("Wookiee", "Kashyyyk", "Star Wars");
        AlienSpecies alien3 = new AlienSpecies("Kryptonian", "Krypton", "DC Comics");
        
        System.out.println(alien1);
        System.out.println(alien2);
        System.out.println(alien3);
        
        System.out.println("\n=== Testing Getters ===");
        System.out.println("Name: " + alien1.getName());
        System.out.println("Planet: " + alien1.getPlanet());
        System.out.println("Universe: " + alien1.getUniverse());
    }
}

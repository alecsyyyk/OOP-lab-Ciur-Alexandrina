package lab3;
public class AlienSpecies {
    private String name;
    private String planet;
    private String universe;

    public AlienSpecies(String name, String planet, String universe) {
        this.name = name;
        this.planet = planet;
        this.universe = universe;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }
    
    public String getUniverse() {
        return universe;
    }

    public String toString() {
        return name + " from planet " + planet + " (" + universe + " universe)";
    }
}

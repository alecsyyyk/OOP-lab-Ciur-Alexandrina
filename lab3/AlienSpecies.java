package lab3;
import java.util.List;

public class AlienSpecies {
    private String name;
    private String planet;
    private String universe;
    private String classification;
    private String temperament;
    private long population;
    private List<String> abilities;

    public AlienSpecies(String name, String planet, String universe, 
                        String classification, String temperament, 
                        long population, List<String> abilities) {
        this.name = name;
        this.planet = planet;
        this.universe = universe;
        this.classification = classification;
        this.temperament = temperament;
        this.population = population;
        this.abilities = abilities;
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

    public String getClassification() {
        return classification;
    }

    public String getTemperament() {
        return temperament;
    }

    public long getPopulation() {
        return population;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" from planet ").append(planet);
        sb.append(" (").append(universe).append(" universe)\n");
        sb.append("  Classification: ").append(classification).append("\n");
        sb.append("  Temperament: ").append(temperament).append("\n");
        sb.append("  Population: ").append(population).append("\n");
        sb.append("  Abilities: ").append(String.join(", ", abilities));
        return sb.toString();
    }
}

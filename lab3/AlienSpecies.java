package lab3;
import java.util.List;

public class AlienSpecies {
    private int id;
    private Boolean isHumanoid;  
    private String originPlanet; 
    private Integer age;         
    private List<String> physicalTraits; 

    public AlienSpecies(int id, Boolean isHumanoid, String originPlanet, 
                        Integer age, List<String> physicalTraits) {
        this.id = id;
        this.isHumanoid = isHumanoid;
        this.originPlanet = originPlanet;
        this.age = age;
        this.physicalTraits = physicalTraits;
    }

    public int getId() {
        return id;
    }

    public Boolean getIsHumanoid() {
        return isHumanoid;
    }
    
    public String getOriginPlanet() {
        return originPlanet;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getPhysicalTraits() {
        return physicalTraits;
    }

    public boolean hasHumanoidData() {
        return isHumanoid != null;
    }

    public boolean hasPlanetData() {
        return originPlanet != null;
    }

    public boolean hasAgeData() {
        return age != null;
    }

    public boolean hasTraitsData() {
        return physicalTraits != null && !physicalTraits.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Add alien ID
        sb.append("Alien ID: ");
        sb.append(id);
        sb.append("\n");
        
        // Add humanoid status
        sb.append("  Is Humanoid: ");
        if (isHumanoid != null) {
            sb.append(isHumanoid);
        } else {
            sb.append("Unknown");
        }
        sb.append("\n");
        
        // Add planet
        sb.append("  Origin Planet: ");
        if (originPlanet != null) {
            sb.append(originPlanet);
        } else {
            sb.append("Unknown");
        }
        sb.append("\n");
        
        // Add age
        sb.append("  Age: ");
        if (age != null) {
            sb.append(age);
        } else {
            sb.append("Unknown");
        }
        sb.append("\n");
        
        // Add traits
        sb.append("  Physical Traits: ");
        if (hasTraitsData()) {
            sb.append(String.join(", ", physicalTraits));
        } else {
            sb.append("None recorded");
        }
        
        return sb.toString();
    }
}

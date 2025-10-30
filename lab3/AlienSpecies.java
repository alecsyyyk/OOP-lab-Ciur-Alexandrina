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
        sb.append("Alien ID: ").append(id).append("\n");
        sb.append("  Is Humanoid: ").append(isHumanoid != null ? isHumanoid : "Unknown").append("\n");
        sb.append("  Origin Planet: ").append(originPlanet != null ? originPlanet : "Unknown").append("\n");
        sb.append("  Age: ").append(age != null ? age : "Unknown").append("\n");
        
        if (hasTraitsData()) {
            sb.append("  Physical Traits: ").append(String.join(", ", physicalTraits));
        } else {
            sb.append("  Physical Traits: None recorded");
        }
        
        return sb.toString();
    }
}

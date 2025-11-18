package lab3;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AlienView {
    
    // method takes your classification results and writes them to a JSON file
    public void writeClassificationToJson(Map<String, List<AlienSpecies>> classifications, 
                                          String outputFilePath) throws IOException {
        
        // Create a new JSON object to hold all data
        JSONObject output = new JSONObject();
        
        // Go through each category (like "Star Wars (Wookie)", "Marvel (Asgardian)", etc.)
        for (Map.Entry<String, List<AlienSpecies>> entry : classifications.entrySet()) {
            String category = entry.getKey(); 
            List<AlienSpecies> aliens = entry.getValue();  
            
            JSONArray alienArray = new JSONArray();
            
            //Add each alien to the array
            for (AlienSpecies alien : aliens) {
                JSONObject alienJson = convertAlienToJson(alien);
                alienArray.put(alienJson);
            }
            
            output.put(category, alienArray);
        }
        
        // Write the JSON to a file
        FileWriter writer = new FileWriter(outputFilePath);
        writer.write(output.toString(2));  // use 2 spaces for indentation
        writer.close();
    }
    
    //converts one AlienSpecies object into JSON format
    private JSONObject convertAlienToJson(AlienSpecies alien) {
        JSONObject json = new JSONObject();
     
        json.put("id", alien.getId());
      
        if (alien.hasHumanoidData()) {
            json.put("isHumanoid", alien.getIsHumanoid());
        } else {
            json.put("isHumanoid", JSONObject.NULL); 
        }
      
        if (alien.hasPlanetData()) {
            json.put("planet", alien.getOriginPlanet());
        } else {
            json.put("planet", JSONObject.NULL);
        }
     
        if (alien.hasAgeData()) {
            json.put("age", alien.getAge());
        } else {
            json.put("age", JSONObject.NULL);
        }
        
        if (alien.hasTraitsData()) {
            JSONArray traits = new JSONArray(alien.getPhysicalTraits());
            json.put("traits", traits);
        } else {
            json.put("traits", JSONObject.NULL);
        }
        
        return json;
    }
    
    // This method creates a summary file with just the counts
    public void writeSummaryToJson(Map<String, List<AlienSpecies>> classifications, 
                                   String outputFilePath) throws IOException {
        
        JSONObject summary = new JSONObject();
        
        int totalAliens = 0;
        for (Map.Entry<String, List<AlienSpecies>> entry : classifications.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue().size();
            
            summary.put(category, count);  
            totalAliens = totalAliens + count;
        }
        
        summary.put("total", totalAliens);
        
        FileWriter writer = new FileWriter(outputFilePath);
        writer.write(summary.toString(2));
        writer.close();
    }
}

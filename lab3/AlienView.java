package lab3;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AlienView {
    
    // This method takes your classification results and writes them to a JSON file
    public void writeClassificationToJson(Map<String, List<AlienSpecies>> classifications, 
                                          String outputFilePath) throws IOException {
        
        // Step 1: Create a new JSON object to hold all data
        JSONObject output = new JSONObject();
        
        // Step 2: Go through each category (like "Star Wars (Wookie)", "Marvel (Asgardian)", etc.)
        for (Map.Entry<String, List<AlienSpecies>> entry : classifications.entrySet()) {
            String category = entry.getKey();  // e.g., "Star Wars (Wookie)"
            List<AlienSpecies> aliens = entry.getValue();  // all aliens in this category
            
            // Step 3: Create a JSON array for this category
            JSONArray alienArray = new JSONArray();
            
            // Step 4: Add each alien to the array
            for (AlienSpecies alien : aliens) {
                JSONObject alienJson = convertAlienToJson(alien);
                alienArray.put(alienJson);
            }
            
            // Step 5: Add this category and its aliens to the output
            output.put(category, alienArray);
        }
        
        // Step 6: Write the JSON to a file
        FileWriter writer = new FileWriter(outputFilePath);
        writer.write(output.toString(2));  // 2 means: use 2 spaces for indentation
        writer.close();
    }
    
    // This method converts one AlienSpecies object into JSON format
    private JSONObject convertAlienToJson(AlienSpecies alien) {
        JSONObject json = new JSONObject();
        
        // Add ID (always present)
        json.put("id", alien.getId());
        
        // Add isHumanoid (if available)
        if (alien.hasHumanoidData()) {
            json.put("isHumanoid", alien.getIsHumanoid());
        } else {
            json.put("isHumanoid", JSONObject.NULL);  // NULL means: data is missing
        }
        
        // Add planet (if available)
        if (alien.hasPlanetData()) {
            json.put("planet", alien.getOriginPlanet());
        } else {
            json.put("planet", JSONObject.NULL);
        }
        
        // Add age (if available)
        if (alien.hasAgeData()) {
            json.put("age", alien.getAge());
        } else {
            json.put("age", JSONObject.NULL);
        }
        
        // Add traits (if available)
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
        
        // Step 1: Create JSON object for summary
        JSONObject summary = new JSONObject();
        
        // Step 2: Count aliens in each category
        int totalAliens = 0;
        for (Map.Entry<String, List<AlienSpecies>> entry : classifications.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue().size();
            
            summary.put(category, count);  // e.g., "Star Wars (Wookie)": 5
            totalAliens = totalAliens + count;
        }
        
        // Step 3: Add total count
        summary.put("total", totalAliens);
        
        // Step 4: Write to file
        FileWriter writer = new FileWriter(outputFilePath);
        writer.write(summary.toString(2));
        writer.close();
    }
}

package lab3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AlienReader {
    
    public static void main(String[] args) {
        System.out.println("Reading aliens.json file:\n");
        
        try {
            String content = Files.readString(Paths.get("aliens.json"));
            
            // Print the raw file content
            System.out.println("Raw file content:");
            System.out.println(content);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

package task3;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DisplayReader {
    public static List<Display> readDisplaysFromFile(String filePath) {
        List<Display> displays = new ArrayList<>();
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            
            for (String line : lines) {
                String[] parts = line.split(",");
                
                int width = Integer.parseInt(parts[0].trim());
                int height = Integer.parseInt(parts[1].trim());
                float ppi = Float.parseFloat(parts[2].trim());
                String model = parts[3].trim();
                
                Display display = new Display(width, height, ppi, model);
                displays.add(display);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return displays;
    }
}

package task3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Read displays from file
        List<Display> displays = DisplayReader.readDisplaysFromFile("task3/displays.txt");

        // Create assistant
        Assistant assistant = new Assistant("Tech Helper");

        // Assign all displays
        for(Display d : displays) {
            assistant.assignDisplay(d);
        }

        // Compare all displays
        assistant.assist();

        // Buy a display
        if (!displays.isEmpty()) {
            Display bought = assistant.buyDisplay(displays.get(0));
            System.out.println("\nYou bought: " + bought.model);
        }
    }
}

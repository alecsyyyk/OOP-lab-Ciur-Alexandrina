package task3;

import java.util.*;

public class Assistant {
    private String assistantName;
    private List<Display> assignedDisplays;

    public Assistant(String assistantName){
        this.assistantName = assistantName;
        this.assignedDisplays = new ArrayList<>();
    }

    public void assignDisplay(Display d){
        assignedDisplays.add(d);
    }

    public void assist() {
        System.out.println(assistantName + " is assisting you:");
        
        for (int i = 0; i < assignedDisplays.size() - 1; i++) {
            Display current = assignedDisplays.get(i);
            Display next = assignedDisplays.get(i + 1);
            
            current.compareWithMonitor(next);
        }
    }

    public Display buyDisplay(Display d) {
        assignedDisplays.remove(d);
        return d;
    }
}
import java.io.*;
import java.util.*;

public class ChecklistManager {
    private List<String> checklist = new ArrayList<>();

    public void setChecklist(List<String> items) {
        checklist = new ArrayList<>(items);
    }

    public List<String> getChecklist() {
        return checklist;
    }

    public void addItem(String item) {
        checklist.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < checklist.size()) {
            checklist.remove(index);
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String item : checklist) {
                writer.println(item);
            }
        } catch (IOException e) {
            System.out.println("Error saving checklist: " + e.getMessage());
        }
    }
}
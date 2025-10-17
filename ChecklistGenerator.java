import java.util.*;

public class ChecklistGenerator {
    public static List<String> generateChecklist(TripProfile profile) {
        List<String> items = new ArrayList<>();

        items.addAll(List.of("Toothbrush", "Clothes", "Phone Charger", "Passport"));

        switch (profile.getDestination()) {
            case BEACH -> items.addAll(List.of("Sunscreen", "Swimwear", "Flip-flops"));
            case MOUNTAINS -> items.addAll(List.of("Jacket", "Boots", "Thermal wear"));
            case CITY -> items.add("Casual wear");
            case ABROAD -> items.addAll(List.of("Travel adapter", "Currency", "Visa"));
        }

        if (profile.getDuration() == TripProfile.Duration.LONG) {
            items.add("Extra clothes");
            items.add("Laundry bag");
        }

        if (profile.isHiking()) items.add("Hiking gear");
        if (profile.isPhotography()) items.add("Camera");
        if (profile.isBusiness()) items.add("Formal wear");

        return items;
    }
}
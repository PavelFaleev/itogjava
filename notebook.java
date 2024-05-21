import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>(Arrays.asList(
                new Notebook("Dell", 8, 256, "Windows 10", "Black"),
                new Notebook("HP", 16, 512, "Windows 10", "Silver"),
                new Notebook("Apple", 8, 256, "macOS", "Gray"),
                new Notebook("Asus", 32, 1024, "Windows 10", "Black"),
                new Notebook("Lenovo", 16, 256, "Linux", "White")
        ));

        Map<String, Object> filterCriteria = getFilterCriteriaFromUser();
        Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, filterCriteria);
        
        System.out.println("Filtered Notebooks:");
        filteredNotebooks.forEach(System.out::println);
    }

    private static Map<String, Object> getFilterCriteriaFromUser() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        System.out.println("Enter the number corresponding to the desired criterion:");
        System.out.println("1 - RAM (in GB)");
        System.out.println("2 - Storage (in GB)");
        System.out.println("3 - Operating System");
        System.out.println("4 - Color");

        while (true) {
            int criterion = scanner.nextInt();
            switch (criterion) {
                case 1:
                    System.out.print("Enter minimum RAM (in GB): ");
                    criteria.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.print("Enter minimum storage (in GB): ");
                    criteria.put("storage", scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Enter operating system: ");
                    criteria.put("os", scanner.next());
                    break;
                case 4:
                    System.out.print("Enter color: ");
                    criteria.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    continue;
            }
            System.out.print("Do you want to add another criterion? (yes/no): ");
            if (scanner.next().equalsIgnoreCase("no")) {
                break;
            }
            System.out.println("Enter the number corresponding to the desired criterion:");
            System.out.println("1 - RAM (in GB)");
            System.out.println("2 - Storage (in GB)");
            System.out.println("3 - Operating System");
            System.out.println("4 - Color");
        }
        return criteria;
    }

    private static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Object> criteria) {
        return notebooks.stream().filter(notebook -> {
            boolean matches = true;
            if (criteria.containsKey("ram")) {
                matches = matches && notebook.getRam() >= (int) criteria.get("ram");
            }
            if (criteria.containsKey("storage")) {
                matches = matches && notebook.getStorage() >= (int) criteria.get("storage");
            }
            if (criteria.containsKey("os")) {
                matches = matches && notebook.getOs().equalsIgnoreCase((String) criteria.get("os"));
            }
            if (criteria.containsKey("color")) {
                matches = matches && notebook.getColor().equalsIgnoreCase((String) criteria.get("color"));
            }
            return matches;
        }).collect(Collectors.toSet());
    }
}

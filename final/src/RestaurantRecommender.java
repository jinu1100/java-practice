import java.util.*;

class Restaurant {
    private String name;
    private Set<String> categories;

    public Restaurant(String name, Set<String> categories) {
        this.name = name;
        this.categories = new HashSet<>();
        for (String category : categories) {
            this.categories.add(category.toLowerCase().trim());
        }
    }

    public String getName() {
        return name;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public boolean hasCategory(String category) {
        return categories.contains(category.toLowerCase().trim());
    }

    @Override
    public String toString() {
        return name + " (" + String.join(", ", categories) + ")";
    }
}

public class RestaurantRecommender {
    private static List<Restaurant> restaurantList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 음식점 프로그램 ===");
            System.out.println("1. 음식점 등록");
            System.out.println("2. 카테고리 추천");
            System.out.println("3. 종료");
            System.out.print("원하는 번호를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRestaurantsUntilStop();
                    break;
                case "2":
                    recommendByCategory();
                    break;
                case "3":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 선택해주세요.");
            }
        }
    }

    private static void addRestaurantsUntilStop() {
        System.out.println("\n[ 음식점 등록 시작 ] '그만' 입력 시 종료");

        while (true) {
            System.out.print("음식점 이름 입력 (그만하려면 '그만'): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("그만")) {
                break;
            }

            System.out.print("카테고리 입력 (쉼표로 구분, 예: 한식, 중식, 분식): ");
            String[] categoryInput = scanner.nextLine().split(",");
            Set<String> categorySet = new HashSet<>(Arrays.asList(categoryInput));

            restaurantList.add(new Restaurant(name, categorySet));
            System.out.println(">> 음식점 등록 완료!\n");
        }
    }

    private static void recommendByCategory() {
        if (restaurantList.isEmpty()) {
            System.out.println("\n[알림] 등록된 음식점이 없습니다.");
            return;
        }

        System.out.print("\n추천 받을 카테고리를 입력하세요: ");
        String inputCategory = scanner.nextLine().toLowerCase().trim();

        System.out.println("\n[" + inputCategory + "] 카테고리 추천 음식점:");

        boolean found = false;
        for (Restaurant r : restaurantList) {
            if (r.hasCategory(inputCategory)) {
                System.out.println("- " + r);
                found = true;
            }
        }

        if (!found) {
            System.out.println("해당 카테고리에 맞는 음식점이 없습니다.");
        }
    }
}

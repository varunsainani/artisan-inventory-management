package dashboards;

import commands.SubmitProductCommand;
import commands.ViewMyProductsCommand;
import utils.InputUtils;

public class ArtisanDashboard {
    private final String artisanId;

    public ArtisanDashboard(String artisanId) {
        this.artisanId = artisanId;
    }

    public void show() {
        while (true) {
            System.out.println("\nArtisan Dashboard");
            System.out.println("1. Register Product");
            System.out.println("2. View My Products");
            System.out.println("3. Logout");
            int choice = InputUtils.nextInt("Choose option: ");

            switch (choice) {
                case 1 -> new SubmitProductCommand(artisanId).execute();
                case 2 -> new ViewMyProductsCommand(artisanId).execute();
                case 3 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
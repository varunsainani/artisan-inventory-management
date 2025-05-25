package dashboards;

import commands.*;
import utils.InputUtils;

public class AdminDashboard {
    public void show() {
        while (true) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. Approve Artisan Registrations");
            System.out.println("2. Approve Product Listings");
            System.out.println("3. View Inventory");
            System.out.println("4. Record Orders");
            System.out.println("5. Generate Packing List");
            System.out.println("6. View Commissions");
            System.out.println("7. Logout");
            int choice = InputUtils.nextInt("Choose option: ");

            switch (choice) {
                case 1 -> new ApproveArtisanCommand().execute();
                case 2 -> new ApproveProductCommand().execute();
                case 3 -> new ViewInventoryCommand().execute();
                case 4 -> new RecordOrderCommand().execute();
                case 5 -> new PackingListCommand().execute();
                case 6 -> new ViewCommissionCommand().execute();
                case 7 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}

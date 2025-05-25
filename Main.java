import commands.LoginCommand;
import commands.RegisterArtisanCommand;
import dashboards.AdminDashboard;
import dashboards.ArtisanDashboard;
import models.Artisan;
import models.User;
import utils.FileUtils;
import utils.InputUtils;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nArtisan Cooperative Inventory Management System");
            System.out.println("1. Login");
            System.out.println("2. Register as Artisan");
            System.out.println("3. Exit");
            int choice = InputUtils.nextInt("Choose option: ");

            switch (choice) {
                case 1 -> {
                    LoginCommand login = new LoginCommand();
                    login.execute();
                    User user = login.getLoggedInUser();
                    if (user != null) {
                        if (user.getRole().equalsIgnoreCase("admin")) {
                            new AdminDashboard().show();
                        } else if (user.getRole().equalsIgnoreCase("artisan")) {
                            boolean approved = false;
                            for (String line : FileUtils.readLines("data/artisans.txt")) {
                                Artisan artisan = Artisan.fromFileString(line);
                                if (artisan != null && artisan.getStatus().equalsIgnoreCase("Approved") &&
                                        user.getUsername().equalsIgnoreCase(artisan.getName())) {
                                    approved = true;
                                    new ArtisanDashboard(artisan.getArtisanId()).show();
                                    break;
                                }
                            }
                            if (!approved) {
                                System.out.println("Your artisan registration is still pending approval.");
                            }
                        }
                    }
                }
                case 2 -> new RegisterArtisanCommand().execute();
                case 3 -> {
                    System.out.println("Exiting system.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
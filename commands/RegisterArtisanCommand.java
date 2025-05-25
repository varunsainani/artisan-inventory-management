package commands;

import models.Artisan;
import utils.FileUtils;
import utils.IDGenerator;
import utils.InputUtils;

public class RegisterArtisanCommand implements Command {
    @Override
    public void execute() {
        String name = InputUtils.nextLine("Enter name: ");
        String contact = InputUtils.nextLine("Enter contact: ");
        String username = InputUtils.nextLine("Choose a username: ");
        String password = InputUtils.nextLine("Choose a password: ");

        String artisanId = IDGenerator.generateId("ART");
        Artisan artisan = new Artisan(artisanId, name, contact, "Pending");
        FileUtils.appendLine("data/artisans.txt", artisan.toFileString());
        FileUtils.appendLine("data/users.txt", username + "|" + password + "|artisan");

        System.out.println("Artisan registered successfully. Awaiting admin approval.");
    }
}
package commands;

import models.Artisan;
import utils.FileUtils;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class ApproveArtisanCommand implements Command {
    @Override
    public void execute() {
        List<String> lines = FileUtils.readLines("data/artisans.txt");
        List<Artisan> pending = new ArrayList<>();

        for (String line : lines) {
            Artisan artisan = Artisan.fromFileString(line);
            if (artisan != null && artisan.getStatus().equalsIgnoreCase("Pending")) {
                pending.add(artisan);
            }
        }

        if (pending.isEmpty()) {
            System.out.println("No pending artisan registrations.");
            return;
        }

        for (int i = 0; i < pending.size(); i++) {
            Artisan artisan = pending.get(i);
            System.out.println((i + 1) + ". " + artisan.getName() + " (" + artisan.getContact() + ")");
        }

        int choice = InputUtils.nextInt("Approve which artisan? (0 to cancel): ");
        if (choice > 0 && choice <= pending.size()) {
            Artisan toApprove = pending.get(choice - 1);
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                Artisan art = Artisan.fromFileString(line);
                if (art != null && art.getArtisanId().equals(toApprove.getArtisanId())) {
                    art.setStatus("Approved");
                    updatedLines.add(art.toFileString());
                } else {
                    updatedLines.add(line);
                }
            }
            FileUtils.overwriteFile("data/artisans.txt", updatedLines);
            System.out.println("Artisan approved successfully.");
        }
    }
}
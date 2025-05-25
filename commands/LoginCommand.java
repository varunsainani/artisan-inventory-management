package commands;

import models.User;
import utils.FileUtils;
import utils.InputUtils;

import java.util.List;

public class LoginCommand implements Command {
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void execute() {
        String username = InputUtils.nextLine("Username: ");
        String password = InputUtils.nextLine("Password: ");

        List<String> userLines = FileUtils.readLines("data/users.txt");
        for (String line : userLines) {
            User user = User.fromFileString(line);
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful as " + user.getRole());
                return;
            }
        }
        System.out.println("Invalid credentials. Try again.");
    }
}

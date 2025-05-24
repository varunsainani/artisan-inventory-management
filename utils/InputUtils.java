package utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String nextLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int nextInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    public static String nextChoice(String prompt, String... options) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            for (String option : options) {
                if (option.equalsIgnoreCase(input)) {
                    return option;
                }
            }
            System.out.println("Invalid choice. Options: " + String.join(", ", options));
        }
    }
}

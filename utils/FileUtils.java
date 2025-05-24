package utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtils {

    public static List<String> readLines(String filepath) {
        try {
            return Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void writeLines(String filepath, List<String> lines) {
        try {
            Files.write(Paths.get(filepath), lines);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filepath);
        }
    }

    public static void appendLine(String filepath, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending to file: " + filepath);
        }
    }

    public static void overwriteFile(String filepath, List<String> lines) {
        writeLines(filepath, lines);
    }
}
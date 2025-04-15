import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    public static List<String> loadFromFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllLines(path);
    }

    public static void saveToFile(String filename, List<String> list) throws IOException {
        Path path = Paths.get(filename);
        Files.write(path, list);
    }

    public static boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
    }
}
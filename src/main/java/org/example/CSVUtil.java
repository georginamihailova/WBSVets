package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtil {
    public static List<String> loadCSV(String filePath) throws IOException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        lines.remove(0); // Remove header
        return lines;
    }
}

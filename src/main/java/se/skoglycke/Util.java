package se.skoglycke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> toStringList(String fileName) {

        List<String> inputs = new ArrayList<>();
        try {
            inputs = Files.readAllLines(Path.of(fileName));
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }
        return inputs;
    }

    public static List<Integer> toIntList(String fileName) {
        List<Integer> inputs = new ArrayList<>();
        try {
            inputs = Files.readAllLines(Path.of(fileName))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }
        return inputs;
    }

    public static String[] getFromPath(final String path) {
        final Path filePath = Paths.get(path);

        try {
            String content = Files.readString(filePath);
            return content.split("\n");
        } catch (Exception e) {
            throw new AssertionError("Fail to load file");
        }
    }

    public static int[] toInt(final String[] strings) {

        final int[] array = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        return array;
    }

}

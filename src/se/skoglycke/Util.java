package se.skoglycke;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {

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

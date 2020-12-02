package se.skoglycke;

import static se.skoglycke.Util.getFromPath;
import static se.skoglycke.Util.toInt;

public class DayOne {

    public static void main(String[] args) {

        final String[] entries = getFromPath("resources/1.txt");
        final int[] ints = toInt(entries);

        System.out.println(stuff(ints));
    }

    private static int stuff(final int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                for (int k = 0; k < ints.length; k++) {
                    if (ints[i] + ints[j] + ints[k] == 2020) {
                        return ints[i] * ints[j] * ints[k];
                    }
                }
            }
        }
        throw new RuntimeException("On nooo!");
    }

}

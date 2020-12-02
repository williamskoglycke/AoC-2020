package se.skoglycke.days;

import se.skoglycke.Calculable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

public class DayOne implements Calculable {

    @Override
    public String getSolution() {

        final String[] entries = Util.getFromPath("src/main/resources/1.txt");
        final int[] ints = Util.toInt(entries);

        return formatAnswer(partOne(ints), partTwo(ints));
    }

    private int partOne(final int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[i] + ints[j] == 2020) {
                    return ints[i] * ints[j];
                }
            }
        }
        throw new NoobiamException("Didn't find anything matching the criteria");
    }

    private int partTwo(final int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                for (int k = 0; k < ints.length; k++) {
                    if (ints[i] + ints[j] + ints[k] == 2020) {
                        return ints[i] * ints[j] * ints[k];
                    }
                }
            }
        }
        throw new NoobiamException("Didn't find anything matching the criteria");
    }

}

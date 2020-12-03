package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.Util;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DayTwo implements Adventable {

    @Override
    public String getSolution() {

        final String[] entries = Util.getFromPath("src/main/resources/2.txt");

        final long countByFirstPolicy = Stream.of(entries).filter(this::isValidForPartOne).count();
        final long countBySecondPolicy = Stream.of(entries).filter(this::isValidForPartTwo).count();

        return formatAnswer(countByFirstPolicy, countBySecondPolicy);
    }

    @Override
    public void test() {
        assertTrue(isValidForPartOne("1-3 a: abcde"));
        assertFalse(isValidForPartOne("1-3 b: cdefg"));
        assertTrue(isValidForPartOne("2-9 c: ccccccccc"));
    }

    private boolean isValidForPartOne(final String entry) {

        final String[] split = entry.split(" ");

        final String validRange = split[0];
        final String[] minMax = validRange.split("-");
        final int minRange = Integer.parseInt(minMax[0]);
        final int maxRange = Integer.parseInt(minMax[1]);

        final String letter = split[1].substring(0, 1); // Removes ":"
        final String password = split[2];

        int occurrences = 0;
        for (final char c : password.toCharArray()) {
            if (String.valueOf(c).equals(letter)) {
                occurrences++;
            }
        }
        return occurrences >= minRange && occurrences <= maxRange;
    }

    private boolean isValidForPartTwo(final String entry) {

        final String[] split = entry.split(" ");

        final String validRange = split[0];
        final String[] positions = validRange.split("-");
        final int firstPosition = Integer.parseInt(positions[0]) - 1;
        final int secondPosition = Integer.parseInt(positions[1]) - 1;

        final String letter = split[1].substring(0, 1); // Removes ":"
        final String password = split[2];

        final char firstChar = password.charAt(firstPosition);
        final char secondChar = password.charAt(secondPosition);
        boolean firstPositionCorrect = String.valueOf(firstChar).equals(letter);
        boolean secondPositionCorrect = String.valueOf(secondChar).equals(letter);

        // Only one should be true
        return (firstPositionCorrect && !secondPositionCorrect) || (!firstPositionCorrect && secondPositionCorrect);
    }

}

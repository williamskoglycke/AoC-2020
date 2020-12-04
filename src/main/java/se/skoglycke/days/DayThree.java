package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayThree implements Adventable {

    @Override
    public String getSolution() throws NoobiamException {
        final String[] entries = Util.getFromPath("src/main/resources/3.txt");

        final int answerPartOne = getTreeHits(entries, 1, 3);

        final Long answerPartTwo = multiply(
                getTreeHits(entries, 1, 1),
                getTreeHits(entries, 1, 3),
                getTreeHits(entries, 1, 5),
                getTreeHits(entries, 1, 7),
                getTreeHits(entries, 2, 1)
        );

        return formatAnswer(answerPartOne, answerPartTwo);
    }

    private int getTreeHits(final String[] entries,
                            final int down,
                            final int right) {

        int toTheRight = right;
        int treesHit = 0;

        for (int i = 1; i < entries.length; i += down) {
            final String line = entries[i];
            final char c = line.charAt(toTheRight % entries[0].length());
            if (String.valueOf(c).equalsIgnoreCase("#")) {
                treesHit++;
            }
            toTheRight += right;
        }

        return treesHit;
    }

    private Long multiply(Integer... values) {

        long stuff = 1;
        for (final Integer value : values) {
            stuff = stuff * value;
        }

        return stuff;
    }

    @Override
    public void test() {
        final String[] entries = Util.getFromPath("src/main/resources/test/3test.txt");

        // Part one
        assertEquals(7, getTreeHits(entries, 1, 3));

        // Part two
        assertEquals(2, getTreeHits(entries, 1, 1));
        assertEquals(7, getTreeHits(entries, 1, 3));
        assertEquals(3, getTreeHits(entries, 1, 5));
        assertEquals(4, getTreeHits(entries, 1, 7));
        assertEquals(2, getTreeHits(entries, 2, 1));

        final Long answerPartTwo = multiply(
                getTreeHits(entries, 1, 1),
                getTreeHits(entries, 1, 3),
                getTreeHits(entries, 1, 5),
                getTreeHits(entries, 1, 7),
                getTreeHits(entries, 2, 1)
        );

        assertEquals(336, answerPartTwo.intValue());
    }
}

package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class Day9 implements Adventable {

    @Override
    public String getSolution() throws NoobiamException {
        final List<Long> entries = Util
                .toStringList("src/main/resources/9.txt")
                .stream()
                .map(Long::parseLong)
                .collect(toList());

        final long partOne = partOne(entries, 25);
        return formatAnswer(partOne, partTwo(entries, partOne));
    }

    @Override
    public void test() {
        final List<Long> entries = Util
                .toStringList("src/main/resources/test/9test.txt")
                .stream()
                .map(Long::parseLong)
                .collect(toList());

        assertEquals(127, partOne(entries, 5));
        assertEquals(62, partTwo(entries, 127));
    }

    public long partTwo(final List<Long> entries,
                        final long brokenNumber) {

        int start = 0;
        long sum = 0;
        int index = 0;
        while (true) {
            if (sum == brokenNumber) {
                final List<Long> longs = entries.subList(start, index);
                longs.sort(Comparator.comparing(Long::longValue));
                return longs.get(0) + longs.get(longs.size() - 1);
            }
            if (sum > brokenNumber) {
                sum = 0;
                start++;
                index = start;
            }
            sum += entries.get(index);
            index++;
        }
    }

    private long partOne(final List<Long> entries,
                         final int preamble) {

        for (int i = preamble; i < entries.size(); i++) {

            final List<Long> subList = entries.subList(i - preamble, i);

            final Long sumCriteria = entries.get(i);
            if (!anyNumberMatchSum(subList, sumCriteria)) {
                return sumCriteria;
            }
        }

        return -1;
    }

    private boolean anyNumberMatchSum(final List<Long> subList,
                                      final long sumCriteria) {

        for (int j = 0; j < subList.size(); j++) {
            for (int k = 0; k < subList.size(); k++) {
                if (subList.get(j) + subList.get(k) == sumCriteria) {
                    return true;
                }
            }
        }
        return false;
    }
}

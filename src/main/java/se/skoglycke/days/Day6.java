package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day6 implements Adventable {

    @Override
    public String getSolution() throws NoobiamException {
        final String[] testEntries = Util.getFromPath("src/main/resources/6.txt");
        final List<String> eachGroup = groupByEmptyLine(testEntries);
        return formatAnswer(partOne(eachGroup), partTwo(eachGroup));
    }

    @Override
    public void test() {
        final String[] testEntries = Util.getFromPath("src/main/resources/test/6test.txt");
        final List<String> eachGroup = groupByEmptyLine(testEntries);

        assertEquals(11, partOne(eachGroup));
        assertEquals(6, partTwo(eachGroup));
    }

    private int partOne(final List<String> eachGroup) {
        return eachGroup
                .stream()
                .map(s -> s.replace(" ", ""))
                .mapToInt(s -> {
                    final HashSet<Character> charSet = new HashSet<>();
                    for (final char c : s.toCharArray()) {
                        charSet.add(c);
                    }
                    return charSet.size();
                })
                .reduce(0, Integer::sum);
    }

    private long partTwo(final List<String> eachGroup) {
        return eachGroup
                .stream()
                .mapToLong(group -> {
                    final String[] peoplesAnswers = group.split(" ");
                    final int noOfPeopleInGroup = peoplesAnswers.length;
                    final HashMap<Character, Integer> charCount = new HashMap<>();

                    for (final String peoplesAnswer : peoplesAnswers) {
                        for (final char c : peoplesAnswer.toCharArray()) {
                            if (charCount.containsKey(c)) {
                                Integer newCount = charCount.get(c) + 1;
                                charCount.put(c, newCount);
                            } else {
                                charCount.put(c, 1);
                            }
                        }
                    }

                    return charCount
                            .values()
                            .stream()
                            .filter(i -> i.equals(noOfPeopleInGroup))
                            .count();
                })
                .reduce(0, Long::sum);
    }

    private List<String> groupByEmptyLine(final String[] testEntries) {
        final ArrayList<String> eachGroup = new ArrayList<>();
        String questions = "";
        for (final String testEntry : testEntries) {
            if (!testEntry.isBlank()) {
                questions += testEntry + " ";
            } else {
                eachGroup.add(questions.trim());
                questions = "";
            }
        }
        eachGroup.add(questions.trim());
        return eachGroup;
    }
}

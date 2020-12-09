package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import static org.junit.Assert.assertEquals;

public class Day7 implements Adventable {

    @Override
    public String getSolution() throws NoobiamException {

        final String[] entries = Util.getFromPath("src/main/resources/7.txt");

        final int i = partOne(entries);
        return String.valueOf(i);
    }

    @Override
    public void test() {
        final String[] entries = Util.getFromPath("src/main/resources/test/7test.txt");
        assertEquals(4, partOne(entries));
    }

    private int partOne(final String[] entries) {
        return -1;
    }

    /*
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
     */

}

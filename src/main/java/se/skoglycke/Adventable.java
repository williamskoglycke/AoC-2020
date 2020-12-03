package se.skoglycke;

public interface Adventable {

    String getSolution() throws NoobiamException;

    void test();

    default String formatAnswer(final Object partOne,
                                final Object partTwo) {

        return this.getClass().getSimpleName() + ": part one: " + partOne + ", part two: " + partTwo;
    }

}

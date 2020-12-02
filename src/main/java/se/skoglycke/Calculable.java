package se.skoglycke;

public interface Calculable {

    String getSolution() throws NoobiamException;

    default String formatAnswer(final Object partOne,
                                final Object partTwo) {

        return this.getClass().getSimpleName() + ": part one: " + partOne + ", part two: " + partTwo;
    }

}

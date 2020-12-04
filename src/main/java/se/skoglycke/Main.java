package se.skoglycke;

import se.skoglycke.days.DayFour;
import se.skoglycke.days.DayOne;
import se.skoglycke.days.DayThree;
import se.skoglycke.days.DayTwo;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final List<Adventable> adventables = List.of(
                new DayOne(),
                new DayTwo(),
                new DayThree(),
                new DayFour()
        );

        try {
            adventables.forEach(Adventable::test);
            adventables.stream().map(Adventable::getSolution).forEach(System.out::println);
        } catch (NoobiamException e) {
            System.out.println(e.getMessage());
        }
    }

}

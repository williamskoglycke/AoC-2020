package se.skoglycke;

import se.skoglycke.days.DayOne;
import se.skoglycke.days.DayTwo;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final List<Calculable> calculables = List.of(
                new DayOne(),
                new DayTwo()
        );

        try {
            calculables.stream().map(Calculable::getSolution).forEach(System.out::println);
        } catch (NoobiamException e) {
            System.out.println(e.getMessage());
        }
    }

}

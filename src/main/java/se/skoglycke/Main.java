package se.skoglycke;

import se.skoglycke.days.Day4;
import se.skoglycke.days.Day1;
import se.skoglycke.days.Day3;
import se.skoglycke.days.Day2;
import se.skoglycke.days.Day5;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final List<Adventable> adventables = List.of(
                new Day1(),
                new Day2(),
                new Day3(),
                new Day4(),
                new Day5()
        );

        try {
            adventables.forEach(Adventable::test);
            adventables.stream().map(Adventable::getSolution).forEach(System.out::println);
        } catch (NoobiamException e) {
            System.out.println(e.getMessage());
        }
    }

}

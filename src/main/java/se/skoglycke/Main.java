package se.skoglycke;

import se.skoglycke.days.Day4;
import se.skoglycke.days.Day1;
import se.skoglycke.days.Day3;
import se.skoglycke.days.Day2;
import se.skoglycke.days.Day5;
import se.skoglycke.days.Day6;
import se.skoglycke.days.Day7;
import se.skoglycke.days.Day8;
import se.skoglycke.days.Day9;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final List<Adventable> adventables = List.of(
                new Day1(),
                new Day2(),
                new Day3(),
                new Day4(),
                new Day5(),
                new Day6(),
                //new Day7(),
                new Day8(),
                new Day9()
        );

        try {
            adventables.forEach(Adventable::test);
            adventables.stream().map(Adventable::getSolution).forEach(System.out::println);
        } catch (NoobiamException e) {
            System.out.println(e.getMessage());
        }
    }

}

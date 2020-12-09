package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static se.skoglycke.days.Day8.Operation.ACCUMULATOR;
import static se.skoglycke.days.Day8.Operation.JUMP;
import static se.skoglycke.days.Day8.Operation.NO_OPERATION;

public class Day8 implements Adventable {

    @Override
    public String getSolution() throws NoobiamException {
        final String[] entries = Util.getFromPath("src/main/resources/8.txt");

        return formatAnswer(partOne(entries), partTwo(entries));
    }

    @Override
    public void test() {
        final String[] entries = Util.getFromPath("src/main/resources/test/8test.txt");

        // Part one
        assertEquals(5, partOne(entries));

        // Part two
        assertEquals(8, partTwo(entries));
    }

    public int partOne(final String[] entries) {

        final List<Instruction> instructions = getInstructions(entries);

        int accumulator = 0;
        int index = 0;

        do {
            final Instruction instruction = instructions.get(index);

            if (instruction.hasRun) {
                break;
            } else {
                instruction.hasRun = true;
            }

            if (instruction.operation == ACCUMULATOR) {
                accumulator += instruction.value;
                index++;
            }
            if (instruction.operation == JUMP) {
                index = index + instruction.value;
            }
            if (instruction.operation == NO_OPERATION) {
                index++;
            }

        } while (true);

        return accumulator;
    }

    private int partTwo(final String[] entries) {

        for (int i = 0; i < entries.length; i++) {

            final List<Instruction> instructions = getInstructions(entries);

            final Operation operation = instructions.get(i).operation;
            if (operation == NO_OPERATION) {
                instructions.get(i).operation = JUMP;
            } else if (operation == JUMP) {
                instructions.get(i).operation = NO_OPERATION;
            }

            int accumulator = 0;
            int index = 0;

            do {
                final Instruction instruction = instructions.get(index);

                if (instruction.hasRun) {
                    break;
                } else {
                    instruction.hasRun = true;
                }

                if (instruction.operation == ACCUMULATOR) {
                    accumulator += instruction.value;
                    index++;
                }
                if (instruction.operation == JUMP) {
                    index = index + instruction.value;
                }
                if (instruction.operation == NO_OPERATION) {
                    index++;
                }
                if (index == entries.length) {
                    return accumulator;
                }
            } while (true);

        }

        return -1;
    }

    private List<Instruction> getInstructions(final String[] entries) {
        return Stream
                .of(entries)
                .map(entry -> {
                    final String[] s = entry.split(" ");
                    if (s[0].equals("acc")) return new Instruction(ACCUMULATOR, Integer.parseInt(s[1]));
                    if (s[0].equals("jmp")) return new Instruction(JUMP, Integer.parseInt(s[1]));
                    return new Instruction(NO_OPERATION, 0);
                })
                .collect(toList());
    }

    public enum Operation {
        NO_OPERATION, JUMP, ACCUMULATOR
    }

    public static class Instruction {

        public Operation operation;
        public final Integer value;
        public boolean hasRun;

        public Instruction(final Operation operation,
                           final Integer value) {
            this.operation = operation;
            this.value = value;
        }
    }

}

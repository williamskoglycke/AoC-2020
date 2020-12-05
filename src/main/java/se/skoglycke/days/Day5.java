package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class Day5 implements Adventable {

    private static final char ROW_UPPER = 'B';
    private static final char ROW_LOWER = 'F';
    private static final char COLUMN_UPPER = 'R';
    private static final char COLUMN_LOWER = 'L';

    @Override
    public String getSolution() throws NoobiamException {
        final String[] entries = Util.getFromPath("src/main/resources/5.txt");

        return formatAnswer(partOne(entries), partTwo(entries));
    }

    @Override
    public void test() {
        assertEquals(new BoardingPass(44, 5, 357), getBoardingPass("FBFBBFFRLR"));
        assertEquals(new BoardingPass(70, 7, 567), getBoardingPass("BFFFBBFRRR"));
        assertEquals(new BoardingPass(14, 7, 119), getBoardingPass("FFFBBBFRRR"));
        assertEquals(new BoardingPass(102, 4, 820), getBoardingPass("BBFFBBFRLL"));
    }

    private Integer partOne(final String[] entries) {
        return Stream
                .of(entries)
                .map(this::getBoardingPass)
                .map(boardingPass -> boardingPass.seatId)
                .max(Integer::compare)
                .orElseThrow(() -> new NoobiamException("whoopsie"));
    }

    private Integer partTwo(final String[] entries) {
        final List<BoardingPass> sortedBySeatId = Stream
                .of(entries)
                .map(this::getBoardingPass)
                .sorted(Comparator.comparing(b -> b.seatId))
                .collect(toList());

        for (int i = 0; i < sortedBySeatId.size(); i++) {
            final BoardingPass boardingPass = sortedBySeatId.get(i);
            if (boardingPass.seatId + 1 != sortedBySeatId.get(i+1).seatId) {
                return boardingPass.seatId + 1;
            }
        }
        throw new NoobiamException("Big kabrak!");
    }

    private BoardingPass getBoardingPass(final String input) {

        // Split input in 5 chars and 3 chars
        final String rowInput = input.substring(0, 7);
        final String columnInput = input.substring(7);

        final int row = stuff(rowInput, 0, 127);
        final int column = stuff(columnInput, 0, 7);
        final int seatId = (row * 8) + column;

        return new BoardingPass(row, column, seatId);
    }

    private int stuff(final String input,
                      int minRange,
                      int maxRange) {

        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c == ROW_UPPER || c == COLUMN_UPPER) {
                minRange = (maxRange + minRange) / 2;
                minRange++;
            }
            if (c == ROW_LOWER || c == COLUMN_LOWER) {
                maxRange = (maxRange + minRange) / 2;
            }
        }

        if (minRange != maxRange) {
            throw new NoobiamException("Big b00m!");
        }

        return minRange;
    }

    public static class BoardingPass {
        public int row;
        public int column;
        public int seatId;

        public BoardingPass(final int row, final int column, final int seatId) {
            this.row = row;
            this.column = column;
            this.seatId = seatId;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final BoardingPass that = (BoardingPass) o;

            if (row != that.row) return false;
            if (column != that.column) return false;
            return seatId == that.seatId;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            result = 31 * result + seatId;
            return result;
        }

        @Override
        public String toString() {
            return "Row: %d, Column: %d, SeatId: %d".formatted(row, column, seatId);
        }
    }

}

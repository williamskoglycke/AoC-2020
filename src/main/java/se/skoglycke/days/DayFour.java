package se.skoglycke.days;

import se.skoglycke.Adventable;
import se.skoglycke.NoobiamException;
import se.skoglycke.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayFour implements Adventable {

    private static final String EMPTY = "";

    private static final String BIRTH_YEAR = "byr";
    private static final String ISSUE_YEAR = "iyr";
    private static final String EXPIRATION_YEAR = "eyr";
    private static final String HEIGHT = "hgt";
    private static final String HAIR_COLOR = "hcl";
    private static final String EYE_COLOR = "ecl";
    private static final String PASSPORT_ID = "pid";
    private static final String COUNTRY_ID = "cid";

    private static final Pattern NON_NUMERIC = Pattern.compile("\\D");

    @Override
    public String getSolution() throws NoobiamException {
        final String[] entries = Util.getFromPath("src/main/resources/4.txt");
        final ArrayList<String> passports = createPassport(entries);

        final long countPartOne = passports.stream().filter(this::isValidPartOne).count();
        final long countPartTwo = passports.stream().filter(this::isValidPartTwo).count();
        return formatAnswer(countPartOne, countPartTwo);
    }

    @Override
    public void test() {
        // Part one
        final String[] entries = Util.getFromPath("src/main/resources/test/4testPartOne.txt");
        final ArrayList<String> separated = createPassport(entries);
        final long count = separated.stream().filter(this::isValidPartOne).count();
        assertEquals(2, count);

        // Part two - Valid
        final String[] entriesPartTwoValid = Util.getFromPath("src/main/resources/test/4testPartTwoValid.txt");
        final ArrayList<String> separatedPartTwoValid = createPassport(entriesPartTwoValid);
        final long countPartTwoValid = separatedPartTwoValid.stream().filter(this::isValidPartTwo).count();
        assertEquals(4, countPartTwoValid);

        // Part two - Invalid
        final String[] entriesPartTwoInvalid = Util.getFromPath("src/main/resources/test/4testPartTwoInvalid.txt");
        final ArrayList<String> separatedPartTwoInvalid = createPassport(entriesPartTwoInvalid);
        final long countPartTwoInvalid = separatedPartTwoInvalid.stream().filter(this::isValidPartTwo).count();
        assertEquals(0, countPartTwoInvalid);

        final String[] realEntries = Util.getFromPath("src/main/resources/4.txt");
        final ArrayList<String> realPassports = createPassport(realEntries);
        final long countPartOne = realPassports.stream().filter(this::isValidPartOne).count();
        final long countPartTwo = realPassports.stream().filter(this::isValidPartTwo).count();
        assertEquals(213, countPartOne);
        assertEquals(147, countPartTwo);
    }

    private boolean isValidPartOne(final String passport) {

        final Map<String, String> passportKeyValues = getPassportKeyValues(passport);

        return checkPartOneCriteria(passportKeyValues);
    }

    private boolean isValidPartTwo(final String passport) {

        final Map<String, String> passportKeyValues = getPassportKeyValues(passport);

        return checkPartTwoCriteria(passportKeyValues);
    }

    private Map<String, String> getPassportKeyValues(final String passport) {
        final String[] keyValue = passport.split(" ");

        return Stream
                .of(keyValue)
                .collect(Collectors
                        .toMap(
                                stuff -> stuff.split(":")[0],
                                stuff -> stuff.split(":")[1]
                        )
                );
    }

    private boolean checkPartOneCriteria(final Map<String, String> passportAttributes) {

        final String birthYear = passportAttributes.getOrDefault(BIRTH_YEAR, EMPTY);
        final String issueYear = passportAttributes.getOrDefault(ISSUE_YEAR, EMPTY);
        final String expirationYear = passportAttributes.getOrDefault(EXPIRATION_YEAR, EMPTY);
        final String height = passportAttributes.getOrDefault(HEIGHT, EMPTY);
        final String hairColor = passportAttributes.getOrDefault(HAIR_COLOR, EMPTY);
        final String eyeColor = passportAttributes.getOrDefault(EYE_COLOR, EMPTY);
        final String passportId = passportAttributes.getOrDefault(PASSPORT_ID, EMPTY);
        final String countryId = passportAttributes.getOrDefault(COUNTRY_ID, EMPTY);

        final long count = Stream
                .of(
                        birthYear,
                        issueYear,
                        expirationYear,
                        height,
                        hairColor,
                        eyeColor,
                        passportId,
                        countryId
                )
                .filter(not(String::isEmpty))
                .count();

        return count == 8 || (count == 7 && countryId.isEmpty());
    }

    private boolean checkPartTwoCriteria(final Map<String, String> passportAttributes) {

        Function<String, Boolean> validBirthYear = year -> year.length() == 4 && Integer.parseInt(year) >= 1920 && Integer.parseInt(year) <= 2002;
        Function<String, Boolean> validIssueYear = year -> year.length() == 4 && Integer.parseInt(year) >= 2010 && Integer.parseInt(year) <= 2020;
        Function<String, Boolean> validExpirationYear = year -> year.length() == 4 && Integer.parseInt(year) >= 2020 && Integer.parseInt(year) <= 2030;
        Function<String, Boolean> validHeight = heightAsString -> {
            final String number = NON_NUMERIC.matcher(heightAsString).replaceAll("");
            if (heightAsString.contains("cm")) {
                return Integer.parseInt(number) >= 150 && Integer.parseInt(number) <= 193;
            }
            if (heightAsString.contains("in")) {
                return Integer.parseInt(number) >= 59 && Integer.parseInt(number) <= 76;
            }
            return false;
        };
        Function<String, Boolean> validHairColor = color -> color.matches("[#][0-f]{6}");
        Function<String, Boolean> validEyeColor = color -> List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(color);
        Function<String, Boolean> validPassportNumber = passportNumber -> passportNumber.length() == 9;

        final long count = passportAttributes
                .entrySet()
                .stream()
                .map(entry -> {
                    if (entry.getKey().equals(BIRTH_YEAR)) return validBirthYear.apply(entry.getValue());
                    if (entry.getKey().equals(ISSUE_YEAR)) return validIssueYear.apply(entry.getValue());
                    if (entry.getKey().equals(EXPIRATION_YEAR)) return validExpirationYear.apply(entry.getValue());
                    if (entry.getKey().equals(HEIGHT)) return validHeight.apply(entry.getValue());
                    if (entry.getKey().equals(HAIR_COLOR)) return validHairColor.apply(entry.getValue());
                    if (entry.getKey().equals(EYE_COLOR)) return validEyeColor.apply(entry.getValue());
                    if (entry.getKey().equals(PASSPORT_ID)) return validPassportNumber.apply(entry.getValue());
                    return false;
                })
                .filter(b -> b)
                .count();

        return count == 7;
    }

    private ArrayList<String> createPassport(final String[] entries) {
        final ArrayList<String> strings = new ArrayList<>();

        String stuff = "";
        for (final String entry : entries) {
            if (!entry.isBlank()) {
                stuff += entry + " ";
            } else {
                strings.add(stuff.trim()); // Remove trailing white space
                stuff = "";
            }
        }
        // Last entry has no new line, so added outside loop
        strings.add(stuff.trim()); // Remove trailing white space
        return strings;
    }
}

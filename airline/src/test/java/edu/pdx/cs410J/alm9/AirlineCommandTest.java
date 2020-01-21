package edu.pdx.cs410J.alm9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirlineCommandTest {

    private static String[] input = new String[]{
            "-README",
            "Airline",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12"
    };

    private static String[] inputWithoutOptions = new String[]{
            "Airline",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12"
    };

    private static String[] inputWithQuotes = new String[]{
            "-README",
            "'Airline",
            "Name'",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12"
    };

    private static String[] badAirlineInput = new String[]{
            "-README",
            "'Airline",
            "Name",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12"
    };

    @Test (expected = NullPointerException.class)
    public void attemptingToParseNullThrowsException() {
        AirlineCommand.parse(null);
    }

    @Test
    public void validStringDoesNotThrowException() {
        AirlineCommand.parse(new String[]{"-README"});
    }

    @Test
    public void inputWithOptionsReturnsSameOptionsInModel() {
        InputModel rv = AirlineCommand.parse(new String[]{"-README"});
        assertThat(rv.options.contains("-README"), is(true));
    }

    @Test
    public void inputWithoutOptionsReturnsEmptyOptionsInModel() {
        InputModel rv = AirlineCommand.parse(inputWithoutOptions);
        assertThat(rv.options.isEmpty(), is(true));
    }

    @Test
    public void airlineWithoutQuotesIsSuccessful() {
        InputModel rv = AirlineCommand.parse(input);
        assertThat(rv.airline, is("Airline"));
    }

    @Test
    public void airlineWithQuotesIsSuccessful() {
        InputModel rv = AirlineCommand.parse(inputWithQuotes);
        assertThat(rv.airline, is("'Airline Name'"));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void badlyFormedAirlineThrowsException() {
        AirlineCommand.parse(badAirlineInput);
    }
}

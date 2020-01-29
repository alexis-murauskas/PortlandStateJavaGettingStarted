package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.time.format.DateTimeParseException;

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

    private static String[] badInput = new String[]{
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
        AirlineCommand.parse(badInput);
    }

    @Test
    public void correctFlightNumberDoesNotThrowException() {
        InputModel rv = AirlineCommand.parse(input);
        assertThat(rv.flightNumber, is("1"));
    }

    @Test (expected = NumberFormatException.class)
    public void flightNumberCantBeALetter() {
        String[] badFlight = new String[]{
                "-README",
                "Airline",
                "q",
                "Src",
                "11/11/1111",
                "11:11",
                "Dst",
                "12/12/1212",
                "12:12"
        };

        AirlineCommand.parse(badFlight);
    }

    @Test
    public void validAirportCodeDoesNotThrowException() {
        InputModel rv = AirlineCommand.parse(inputWithQuotes);
        assertThat(rv.source, is("Src"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidAirportCodeThrowsException() {
        InputModel rv = AirlineCommand.parse(new String[]{
                "-README",
                "Airline",
                "1",
                "Srceeee",
                "11/11/1111",
                "11:11",
                "Dst",
                "12/12/1212",
                "12:12"
        });
    }

    @Test (expected = DateTimeParseException.class)
    public void invalidDateTimeThrowsException() {
        String[] badTime = new String[]{
                "-README",
                "'Airline",
                "Name'",
                "1",
                "Src",
                "11/11/",
                "1:11",
                "Dst",
                "12/12/1212",
                "12:12"
        };

        AirlineCommand.parse(badTime);
    }

    @Test
    public void validDateTimeSucceeds() {
        InputModel rv = AirlineCommand.parse(input);
        assertThat(rv.departureTime, is("11/11/1111 11:11"));
    }

    @Test
    public void validTextFileNameSucceeds() {
        InputModel rv = AirlineCommand.parse(new String[]{
                "-textFile",
                "airline.txt",
                "Airline",
                "1",
                "Src",
                "11/11/1111",
                "11:11",
                "Dst",
                "12/12/1212",
                "12:12"
        });
        assertThat(rv.options.stream().anyMatch(o -> o.contains("-textFile")), is(true));
        assertThat(rv.options.stream().anyMatch(o -> o.contains("airline.txt")), is(true));
    }

    @Test
    public void invalidTextFileNameSucceeds() {
        InputModel rv = AirlineCommand.parse(new String[]{
                "-textFile",
                "",
                "Airline",
                "1",
                "Src",
                "11/11/1111",
                "11:11",
                "Dst",
                "12/12/1212",
                "12:12"
        });
        assertThat(rv.options.stream().anyMatch(o -> o.contains("-textFile")), is(true));
    }
}

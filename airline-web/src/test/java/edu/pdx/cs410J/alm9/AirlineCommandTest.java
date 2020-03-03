package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirlineCommandTest {

    private static String[] input = new String[]{
            "-host",
            "localhost",
            "-port",
            "8080",
            "-README",
            "Airline",
            "1",
            "PDX",
            "11/11/1111",
            "11:11",
            "AM",
            "ABQ",
            "12/12/1212",
            "12:12",
            "PM"
    };

    private static String[] inputWithoutOptions = new String[]{
            "-host",
            "localhost",
            "-port",
            "8080",
            "Airline",
            "1",
            "PDX",
            "11/11/1111",
            "11:11",
            "AM",
            "ABQ",
            "12/12/1212",
            "12:12",
            "PM"
    };

    private static String[] inputWithQuotes = new String[]{
            "-host",
            "localhost",
            "-port",
            "8080",
            "-README",
            "'Airline",
            "Name'",
            "1",
            "PDX",
            "11/11/1111",
            "11:11",
            "AM",
            "ABQ",
            "12/12/1212",
            "12:12",
            "PM"
    };

    private static String[] badInput = new String[]{
            "-host",
            "localhost",
            "-port",
            "8080",
            "-README",
            "'Airline",
            "Name",
            "1",
            "PDX",
            "11/11/1111",
            "11:11",
            "AM",
            "ABQ",
            "12/12/1212",
            "12:12",
            "PM"
    };

    @Test (expected = NullPointerException.class)
    public void attemptingToParseNullThrowsException() throws ParseException {
        AirlineCommand.parse(null);
    }

    @Test
    public void validStringDoesNotThrowException() throws ParseException {
        AirlineCommand.parse(new String[]{"-README"});
    }

    @Test
    public void inputWithOptionsReturnsSameOptionsInModel() throws ParseException {
        InputModel rv = AirlineCommand.parse(new String[]{"-README"});
        assertThat(rv.options, is(1));
    }

    @Test
    public void inputWithoutAdditionalOptionsReturnsEmptyOptionsInModel() throws ParseException {
        InputModel rv = AirlineCommand.parse(inputWithoutOptions);
        assertThat(rv.options, is(4));
    }

    @Test
    public void airlineWithoutQuotesIsSuccessful() throws ParseException {
        InputModel rv = AirlineCommand.parse(input);
        assertThat(rv.airline, is("Airline"));
    }

    @Test
    public void airlineWithQuotesIsSuccessful() throws ParseException {
        InputModel rv = AirlineCommand.parse(inputWithQuotes);
        assertThat(rv.airline, is("'Airline Name'"));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void badlyFormedAirlineThrowsException() throws ParseException {
        AirlineCommand.parse(badInput);
    }

    @Test
    public void correctFlightNumberDoesNotThrowException() throws ParseException {
        InputModel rv = AirlineCommand.parse(input);
        assertThat(rv.flightNumber, is("1"));
    }

    @Test (expected = NumberFormatException.class)
    public void flightNumberCantBeALetter() throws ParseException {
        String[] badFlight = new String[]{
                "-host",
                "localhost",
                "-port",
                "8080",
                "-README",
                "Airline",
                "q",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        };

        AirlineCommand.parse(badFlight);
    }

    @Test (expected = IllegalArgumentException.class)
    public void unknownCommandLineArgument() throws ParseException {
        String[] badFlight = new String[]{
                "-host",
                "localhost",
                "-port",
                "8080",
                "-README",
                "Airline",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM",
                "fred"
        };

        AirlineCommand.parse(badFlight);
    }

    @Test
    public void validAirportCodeDoesNotThrowException() throws ParseException {
        InputModel rv = AirlineCommand.parse(inputWithQuotes);
        assertThat(rv.source, is("PDX"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidAirportCodeThrowsException() throws ParseException {
        AirlineCommand.parse(new String[]{
                "-host",
                "localhost",
                "-port",
                "8080",
                "-README",
                "Airline",
                "1",
                "PDXeeee",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        });
    }

    @Test (expected = ParseException.class)
    public void invalidDateTimeThrowsException() throws ParseException {
        String[] badTime = new String[]{
                "-host",
                "localhost",
                "-port",
                "8080",
                "'Airline",
                "Name'",
                "1",
                "PDX",
                "11/11/",
                "1:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        };

        AirlineCommand.parse(badTime);
    }

    @Test
    public void validDateTimeWithMixedCase() throws ParseException {
        String[] args = new String[]{
                "-host",
                "localhost",
                "-port",
                "8080",
                "Project3",
                "100",
                "CVG",
                "03/02/2020",
                "12:57",
                "pm",
                "DBQ",
                "03/02/2020",
                "4:00",
                "pm"
        };

        AirlineCommand.parse(args);
    }

    @Test
    public void validDateTimeSucceeds() throws ParseException {
        InputModel rv = AirlineCommand.parse(input);
        assertThat(rv.departureTime, is("11/11/1111 11:11 AM"));
    }


    @Test
    public void correctDatesDoNotThrowException() throws ParseException {
        String departure = "11/11/1111 11:11 AM";
        String arrival = "12/12/1212 12:12 PM";

        AirlineCommand.compareDepartureArrivalTimes(departure, arrival);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidDatesThrowException() throws ParseException {
        String departure = "11/11/1111 11:11 AM";
        String arrival = "10/10/1010 10:10 AM";

        AirlineCommand.compareDepartureArrivalTimes(departure, arrival);
    }

    @Test (expected = IllegalArgumentException.class)
    public void nonexistentAirportCodeThrowsException() throws ParseException {
        AirlineCommand.parse(new String[]{
                "-host",
                "localhost",
                "-port",
                "8080",
                "-README",
                "Airline",
                "1",
                "Src",
                "11/11/1111",
                "11:11",
                "AM",
                "Dst",
                "12/12/1212",
                "12:12",
                "PM"
        });
    }
}

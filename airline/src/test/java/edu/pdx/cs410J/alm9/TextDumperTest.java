package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

public class TextDumperTest {

    private Flight flight1 = new Flight(
            1,
            "PDX",
            Flight.PARSEFORMAT.parse("11/11/1111 11:11 AM"),
            "ABQ",
            Flight.PARSEFORMAT.parse("12/12/1212 12:12 PM")
    );
    private Flight flight2 = new Flight(
            2,
            "PDX",
            Flight.PARSEFORMAT.parse("11/11/1111 11:11 AM"),
            "ABQ",
            Flight.PARSEFORMAT.parse("12/12/1212 12:12 PM")
    );

    public TextDumperTest() throws ParseException {
    }

    @Test
    public void nullArgThrowsException() {
        TextDumper sut = new TextDumper("");
        try {
            sut.dump(null);
        } catch (IOException e) {
            return;
        }
        assert (false);
    }

    @Test
    public void validArgDoesNotThrowException() {
        Airline<Flight> airline = new Airline("Airline");
        TextDumper<Airline<Flight>, Flight> sut = new TextDumper("airline.txt");
        airline.addFlight(flight1);

        try {
            sut.dump(airline);
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    public void airlineWithMultipleFlights() {
        Airline<Flight> airline = new Airline("MultipleFlights");
        TextDumper<Airline<Flight>, Flight> sut = new TextDumper("multiple-flights.txt");

        airline.addFlight(flight1);
        airline.addFlight(flight2);

        try {
            sut.dump(airline);
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    public void airlineWithLongName() {
        Airline<Flight> airline = new Airline("'Long Name'");
        TextDumper<Airline<Flight>, Flight> sut = new TextDumper("long-name.txt");

        airline.addFlight(flight1);
        airline.addFlight(flight2);

        try {
            sut.dump(airline);
        } catch (IOException e) {
            assert (false);
        }
    }
}
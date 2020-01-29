package edu.pdx.cs410J.alm9;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class TextDumperTest {
    @Test
    public void nullArgThrowsException() {
        TextDumper sut = new TextDumper();
        try {
            sut.dump(null);
        } catch (IOException e) {
            return;
        }
        assert (false);
    }

    @Test
    public void validArgDoesNotThrowException() {
        Flight flight = new Flight(
                1,
                "SRC",
                LocalDateTime.parse("11/11/1111 11:11", Flight.DATEFORMAT),
                "DST",
                LocalDateTime.parse("12/12/1212 12:12", Flight.DATEFORMAT)
        );

        Airline<Flight> airline = new Airline("Airline");
        TextDumper<Airline<Flight>, Flight> sut = new TextDumper();
        airline.addFlight(flight);

        try {
            sut.dump(airline);
        } catch (IOException e) {
            assert (false);
        }
    }

    @Test
    public void airlineWithMultipleFlights() {
        Airline<Flight> airline = new Airline("MultipleFlights");
        TextDumper<Airline<Flight>, Flight> sut = new TextDumper();

        airline.addFlight(new Flight(
                1,
                "SRC",
                LocalDateTime.parse("11/11/1111 11:11", Flight.DATEFORMAT),
                "DST",
                LocalDateTime.parse("12/12/1212 12:12", Flight.DATEFORMAT)
        ));

        airline.addFlight(new Flight(
                2,
                "SRC2",
                LocalDateTime.parse("11/11/1111 11:11", Flight.DATEFORMAT),
                "DST2",
                LocalDateTime.parse("12/12/1212 12:12", Flight.DATEFORMAT)
        ));

        try {
            sut.dump(airline);
        } catch (IOException e) {
            assert (false);
        }
    }

}
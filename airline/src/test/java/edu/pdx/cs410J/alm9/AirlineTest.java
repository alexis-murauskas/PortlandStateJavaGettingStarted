package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Airline} class.
 */

public class AirlineTest {

    @Test
    public void airlineMayBeInitializedToNull() {
        Airline airline = new Airline<Flight>();
        assertThat(airline.getName(), is(nullValue()));
    }

    @Test
    public void airlineMayNotHaveFlights() {
        Airline airline = new Airline<Flight>();
        assertThat(airline.getFlights().isEmpty(), equalTo(true));
    }

    @Test
    public void addingFlightDoesNotThrowException() {
        Flight flight = new Flight();
        Airline airline = new Airline<Flight>();
        airline.addFlight(flight);
    }

    @Test
    public void airlineWithNameShouldReturnName() {
        String name = "NAME";
        Airline airline = new Airline<Flight>(name);
        assertThat(airline.getName(), equalTo(name));
    }

    @Test
    public void airlineWithFlightsShouldReturnFlights() {
        Flight flight = new Flight();
        Airline airline = new Airline<Flight>();
        airline.addFlight(flight);
        assertThat(airline.getFlights().size(), equalTo(1));
    }

    @Test
    public void airlineWithMultipleFlights() throws ParseException {
        SimpleDateFormat formatter = Flight.PARSEFORMAT;
        Flight flight1 = new Flight(
                1,
                "SRC",
                formatter.parse("11/11/3333 11:11"),
                "DST",
                formatter.parse("12/12/1212 12:12")
        );

        Flight flight2 = new Flight(
                2,
                "SRC",
                formatter.parse("11/11/2222 11:11"),
                "DST",
                formatter.parse("12/12/1212 12:12")
        );

        Flight flight3 = new Flight(
                3,
                "SRC",
                formatter.parse("11/11/4444 11:11"),
                "DST",
                formatter.parse("12/12/1212 12:12")
        );

        Airline<Flight> airline = new Airline("Airline");
        airline.addFlight(flight1);
        airline.addFlight(flight2);
        airline.addFlight(flight3);

        assertThat(airline.getFlights().toArray()[0], equalTo(flight2));
    }
}

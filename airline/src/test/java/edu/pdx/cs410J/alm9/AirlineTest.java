package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.util.HashSet;

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
        assertThat(airline.getFlights(), equalTo(new HashSet<Flight>()));
    }

    @Test
    public void addingFlightDoesNotThrowException() {
        Flight flight = new Flight();
        Airline airline = new Airline<Flight>();
        airline.addFlight(flight);
    }
}

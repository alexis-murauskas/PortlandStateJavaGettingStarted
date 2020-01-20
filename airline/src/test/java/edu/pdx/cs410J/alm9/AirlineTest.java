package edu.pdx.cs410J.alm9;

import org.junit.Test;

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
}

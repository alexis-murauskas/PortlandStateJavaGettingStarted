package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {

    @Test
    public void flightsMayBeInitializedToNull() {
        Flight flight = new Flight();
        flight.getArrivalString();
    }

    @Test
    public void initiallyAllFlightsHaveTheSameNumber() {
        Flight flight = new Flight();
        assertThat(flight.getNumber(), equalTo(0));
    }

    @Test
    public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
        Flight flight = new Flight();
        assertThat(flight.getDepartureString(), is(nullValue()));
    }

    @Test
    public void initializedFlightReturnsValues() throws ParseException {
        SimpleDateFormat formatter = Flight.PARSEFORMAT;
        Flight flight = new Flight(
                1,
                "SRC",
                formatter.parse("11/11/1111 11:11"),
                "DST",
                formatter.parse("12/12/1212 12:12")
        );

        assertThat(flight.getNumber(), is(1));
        assertThat(flight.getSource() == "SRC", is(true));
        assertThat(flight.getDepartureString(), isA(String.class));
    }


}

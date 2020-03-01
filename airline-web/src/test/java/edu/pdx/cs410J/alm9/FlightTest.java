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

        assertThat(flight.getArrivalString(), nullValue());
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
                "PDX",
                formatter.parse("11/11/1111 11:11 AM"),
                "ABQ",
                formatter.parse("12/12/1212 12:12 PM")
        );

        assertThat(flight.getNumber(), is(1));
        assertThat(flight.getSource().equals("PDX"), is(true));

        var rv = flight.getDepartureString();
        assertThat(rv.equals("11/11/11 11:11 AM"), is(true));
        assertThat(flight.getArrivalString().equals("12/12/12 12:12 PM"), is(true));
    }
}

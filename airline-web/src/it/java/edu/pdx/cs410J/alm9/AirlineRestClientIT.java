package edu.pdx.cs410J.alm9;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Integration test that tests the REST calls made by {@link AirlineRestClient}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AirlineRestClientIT {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    private AirlineRestClient newAirlineRestClient() {
        int port = Integer.parseInt(PORT);
        return new AirlineRestClient(HOSTNAME, port);
    }

    @Test
    public void createClientSucceeds() {
        newAirlineRestClient();
    }

    @Test
    public void createWithInvalidHostPortSucceeds() {
       new AirlineRestClient("", -1);
    }

    @Test
    public void getAirlineSucceeds() throws IOException {
        var client = newAirlineRestClient();
        var rv = client.getAirline("FakeAir");
        assertThat(rv == null, is (true));
    }

    @Test (expected = RuntimeException.class)
    public void getAirlineWithBadHostFails() throws IOException {
        var client = new AirlineRestClient("", -1);
        client.getAirline("Airline");
    }

    @Test
    public void getFlightsSucceeds() throws IOException {
        var client = newAirlineRestClient();
        var rv = client.getAirline("FakeAir", "SRC", "DST");
        assertThat(rv == null, is (true));
    }

    @Test (expected = RuntimeException.class)
    public void getFlightsWithBadHostFails() throws IOException {
        var client = new AirlineRestClient("", -1);
        client.getAirline("FakeAir", "SRC", "DST");
    }

    @Test
    public void postAirlineSucceeds() throws IOException {
        var client = newAirlineRestClient();
        var post = new InputModel();

        post.airline = "Airline";
        post.flightNumber = "1";
        post.source = "PDX";
        post.departureTime = "11/11/1111 11:11 AM";
        post.destination = "ABQ";
        post.arrivalTime = "12/12/1212 12:12 PM";

        client.postAirline(post);
    }

    @Test (expected = RuntimeException.class)
    public void postAirlineFails() throws IOException {
        var client = newAirlineRestClient();
        var post = new InputModel();

        post.airline = "Airline";
        post.flightNumber = "1";
        post.source = "PDX";
        post.departureTime = "11/11/1111 11:11 AM";
        post.destination = "ABQ";

        client.postAirline(post);
    }

    @Test
    public void doNothing() {

    }
}

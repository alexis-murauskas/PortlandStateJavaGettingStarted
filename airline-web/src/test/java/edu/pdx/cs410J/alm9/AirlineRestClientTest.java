package edu.pdx.cs410J.alm9;

import org.junit.Test;

public class AirlineRestClientTest {

    @Test
    public void canCreateClient() {
        new AirlineRestClient("localhost", 8080);
    }

    @Test (expected = AirlineRestException.class)
    public void canThrowAirlineException() {
        throw new AirlineRestException("");
    }
}

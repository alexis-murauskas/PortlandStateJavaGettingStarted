package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.io.IOException;

public class XmlDumperTest {

    @Test
    public void validAirlineSucceeds() throws IOException {
        Airline<Flight> airline = new Airline<>("Airline");

        XmlDumper<Airline<Flight>, Flight> dumper = new XmlDumper<>("airline.xml");
        dumper.dump(airline);
    }

    @Test (expected = IOException.class)
    public void invalidAirlineFails() throws IOException {
        XmlDumper<Airline<Flight>, Flight> dumper = new XmlDumper<>("airline.xml");
        dumper.dump(null);
    }
}

package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class XmlParserTest {

    @Test
    public void parserCanReadInValidFile() throws ParserException {
        XmlParser<Airline<Flight>, Flight> parser = new XmlParser<>("valid-airline.xml");
        parser.parse();
    }

    @Test (expected = ParserException.class)
    public void parserThrowsExceptionForInvalidFIle() throws ParserException {
        XmlParser<Airline<Flight>, Flight> parser = new XmlParser<>("invalid-airline.xml");
        parser.parse();
    }

    @Test
    public void airlineHasCorrectName() throws ParserException {
        XmlParser<Airline<Flight>, Flight> parser = new XmlParser<>("valid-airline.xml");
        Airline<Flight> rv = parser.parse();

        assertThat(!rv.getName().isEmpty(), is(true));
        assertThat(rv.getName(), is("'Valid Airlines'"));
    }

    @Test
    public void airlineHasCorrectNumberOfFlights() throws ParserException {
        XmlParser<Airline<Flight>, Flight> parser = new XmlParser<>("valid-airline.xml");
        Airline<Flight> rv = parser.parse();

        assertThat(rv.getFlights().size(), is(2));
    }

    @Test
    public void flightDataIsCorrect() throws ParserException {
        XmlParser<Airline<Flight>, Flight> parser = new XmlParser<>("valid-airline.xml");
        Airline<Flight> rv = parser.parse();
        Flight flight = (Flight) rv.getFlights().toArray()[0];

        assertThat(flight.getNumber(), is(1437));
        assertThat(flight.getSource(), is("BJX"));
        assertThat(flight.getDepartureString(), is("9/25/20 5:00 PM"));
    }
}

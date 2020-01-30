package edu.pdx.cs410J.alm9;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.pdx.cs410J.ParserException;
import org.junit.Test;

public class TextParserTest {
    public static String testDir = "resources/test/";

    @Test
    public void nonExistentFileNameDoesNotThrowException() {
        TextParser<Airline<Flight>> parser = new TextParser<>("does-not-exist.txt");
        try {
            assertThat(parser.parse(), nullValue());
        } catch (ParserException e) {
            assert(false);
        }
    }

    @Test
    public void correctFileNameDoesNotThrowException() {
        TextParser<Airline<Flight>> parser = new TextParser<>(this.testDir + "airline.txt");
        try {
            parser.parse();
        } catch (ParserException e) {
            assert(false);
        }
    }

    @Test
    public void singleWordAirlineNameReturnsAirline() {
        TextParser<Airline<Flight>> parser = new TextParser<>(this.testDir + "airline.txt");
        try {
            assertThat(parser.parse().getName(), is("Airline"));
        } catch (ParserException e) {
            assert(false);
        }
    }

    @Test
    public void multiWordAirlineNameReturnsAirline() {
        TextParser<Airline<Flight>> parser = new TextParser<>(this.testDir + "long-airline.txt");
        try {
            assertThat(parser.parse().getName(), is("'Airline Name'"));
        } catch (ParserException e) {
            assert(false);
        }
    }

    @Test
    public void multipleFlightsAreAddedSuccessfully() {
        TextParser<Airline<Flight>> parser = new TextParser<>(this.testDir + "airline.txt");
        try {
            assertThat(parser.parse().getFlights().size(), is(2));
        } catch (ParserException e) {
            assert(false);
        }
    }

    @Test
    public void airlineCanHaveNoFlightsAdded() {
        TextParser<Airline<Flight>> parser = new TextParser<>(this.testDir + "long-airline.txt");
        try {
            assertThat(parser.parse().getFlights().size(), is(0));
        } catch (ParserException e) {
            assert(false);
        }
    }

    @Test
    public void malformattedFileThrowsException() {
        TextParser<Airline<Flight>> parser = new TextParser<>(this.testDir + "malformatted.txt");
        try {
            parser.parse();
        } catch (ParserException e) {
            return;
        }
        assert(false);
    }
}

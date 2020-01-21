package edu.pdx.cs410J.alm9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirlineCommandTest {

    @Test (expected = NullPointerException.class)
    public void attemptingToParseNullThrowsException() {
        AirlineCommand.parse(null);
    }

    @Test
    public void parsingValidStringDoesNotThrowException() {
        AirlineCommand.parse(new String[]{"-README"});
    }

    @Test
    public void parsingInputWithOptionsReturnsSameOptionsInModel() {
        InputModel rv = AirlineCommand.parse(new String[]{"-README"});
        assertThat(rv.options.contains("-README"), is(true));
    }

    @Test
    public void parsingInputWithoutOptionsReturnsEmptyOptionsInModel() {
        InputModel rv = AirlineCommand.parse(new String[]{
                "Airline",
                "1",
                "Src",
                "11/11/1111",
                "11:11",
                "Dst",
                "12/12/1212",
                "12:12"
        });
        
        assertThat(rv.options.isEmpty(), is(true));
    }

    @Test
    public void parsingAirlineWithoutQuotesIsSuccessful() {
        InputModel rv = AirlineCommand.parse(new String[]{
                "Airline",
                "1",
                "Src",
                "11/11/1111",
                "11:11",
                "Dst",
                "12/12/1212",
                "12:12"
        });

        assertThat(rv.airline, is("Airline"));
    }
}

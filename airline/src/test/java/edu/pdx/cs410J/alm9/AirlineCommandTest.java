package edu.pdx.cs410J.alm9;

import org.junit.Test;

public class AirlineCommandTest {

    @Test (expected = NullPointerException.class)
    public void attemptingToParseNullThrowsException() {
        AirlineCommand.parse(null);
    }
}

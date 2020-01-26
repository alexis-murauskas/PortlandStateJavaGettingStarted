package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

public class TextParser<T extends AbstractAirline> implements AirlineParser<T> {
    @Override
    public T parse() throws ParserException {
        return null;
    }
}

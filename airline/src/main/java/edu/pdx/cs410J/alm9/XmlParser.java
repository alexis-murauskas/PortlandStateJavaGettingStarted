package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

public class XmlParser<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineParser<T> {
    private String fileName;

    public XmlParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T parse() throws ParserException {
        return null;
    }
}

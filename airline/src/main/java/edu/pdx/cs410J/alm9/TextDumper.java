package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;

public class TextDumper<T extends AbstractAirline> implements AirlineDumper<T> {
    @Override
    public void dump(T airline) throws IOException {

    }
}

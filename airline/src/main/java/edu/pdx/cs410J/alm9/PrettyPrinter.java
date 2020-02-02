package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;

public class PrettyPrinter<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {

    private String fileName;

    public PrettyPrinter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void dump(T airline) throws IOException {

    }
}

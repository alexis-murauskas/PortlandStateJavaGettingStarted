package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class PrettyPrinter<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {

    private String fileName;

    public PrettyPrinter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void dump(T airline) throws IOException {
        if (airline == null)
            throw new IOException();

        String toWrite = "*** " + airline.getName() + " ***\n";
        toWrite += airline.getFlights()
                .stream()
                .map(
                        f -> "\n" + f.getNumber()
                                + " " + f.getSource()
                                + " " + f.getDepartureString()
                                + " " + f.getDestination()
                                + " " + f.getArrivalString()
                                + " (" + ((f.getArrival().getTime()-f.getDeparture().getTime())/60000)
                                + " mins)"
                )
                .collect(Collectors.joining(""));

        try {
            if (this.fileName.equals("-")) {
                System.out.println(toWrite);
                return;
            }

            File file = new File(this.fileName);
            FileWriter writer = new FileWriter(file, true);
            writer.write(toWrite);
            writer.close();

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}

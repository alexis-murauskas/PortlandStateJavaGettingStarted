package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class TextDumper<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {

    private String fileName;

    public TextDumper(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Given an airline object, dump will write it out to a file based on the name of the airline.
     * @param airline to be written out to a file
     * @throws IOException if the parameter is null, or if any errors occur while writing the object to file
     */
    @Override
    public void dump(T airline) throws IOException {
        if (airline == null)
            throw new IOException();

        try {
            File file = new File(this.fileName);
            var isNew = file.createNewFile();
            FileWriter writer = new FileWriter(file, true);

            if (isNew)
                writer.write(airline.getName());

            String flights = airline.getFlights()
                    .stream()
                    .map(
                            f -> "\n" + f.getNumber()
                                    + ";" + f.getSource()
                                    + ";" + Flight.PARSEFORMAT.format(f.getDeparture()).replace(" ", ";")
                                    + ";" + f.getDestination()
                                    + ";" + Flight.PARSEFORMAT.format(f.getArrival()).replace(" ", ";")
                    )
                    .collect(Collectors.joining(""));

            writer.write(flights);
            writer.close();

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}

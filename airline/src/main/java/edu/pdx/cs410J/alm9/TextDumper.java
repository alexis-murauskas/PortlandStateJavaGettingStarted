package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class TextDumper<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {
    public static String ext = ".txt";

    @Override
    public void dump(T airline) throws IOException {
        if (airline == null)
            throw new IOException();

        try {
            File file = new File(airline.getName() + this.ext);
            FileWriter writer = new FileWriter(file);

            if (file.createNewFile()) {
                writer.write(airline.getName() + "\n");
            }

            String flights = airline.getFlights()
                    .stream()
                    .map(
                            f -> f.getNumber()
                                    + ";" + f.getDeparture()
                                    + ";" + f.getDepartureString().replace(" ", ";")
                                    + ";" + f.getArrival()
                                    + ";" + f.getArrivalString().replace(" ", ";")
                    )
                    .collect(Collectors.joining("\n"));

            writer.append(flights);

        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}

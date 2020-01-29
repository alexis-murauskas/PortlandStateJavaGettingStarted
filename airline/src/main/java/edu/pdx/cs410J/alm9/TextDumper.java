package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class TextDumper<T extends AbstractAirline<Q>, Q extends AbstractFlight> implements AirlineDumper<T> {
    public static String dir = "resources/data/";
    public static String ext = ".txt";

    @Override
    public void dump(T airline) throws IOException {
        if (airline == null)
            throw new IOException();

        String name = airline.getName()
                .toLowerCase()
                .replace(" ", "-")
                .replace("'", "");

        try {
            File file = new File(this.dir + name + this.ext);
            var isNew = file.createNewFile();
            FileWriter writer = new FileWriter(file, true);

            if (isNew) {
                writer.write(airline.getName());
            }

            String flights = airline.getFlights()
                    .stream()
                    .map(
                            f -> "\n" + f.getNumber()
                                    + ";" + f.getSource()
                                    + ";" + f.getDepartureString().replace(" ", ";")
                                    + ";" + f.getDestination()
                                    + ";" + f.getArrivalString().replace(" ", ";")
                    )
                    .collect(Collectors.joining(""));

            writer.write(flights);
            writer.close();

        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}

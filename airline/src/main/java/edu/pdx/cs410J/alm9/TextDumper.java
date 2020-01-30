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

    /**
     * Given an airline object, dump will write it out to a file based on the name of the airline.
     * @param airline to be written out to a file
     * @throws IOException if the parameter is null, or if any errors occur while writing the object to file
     */
    @Override
    public void dump(T airline) throws IOException {
        if (airline == null)
            throw new IOException();

        String name = fileFormatAirlineName(airline.getName());

        try {
            File file = new File(name);
            var isNew = file.createNewFile();
            FileWriter writer = new FileWriter(file, true);

            if (isNew)
                writer.write(airline.getName());

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
            throw new IOException(e);
        }
    }

    /**
     * Ensures that all files will be formatted in the same way, which is airline name follows by .txt.
     * @param airline the name of the airline before formatting
     * @return a formatted string
     */
    public static String fileFormatAirlineName (String airline) {
        return airline.toLowerCase()
                .replace(" ", "-")
                .replace("'", "")
                + ext;
    }
}

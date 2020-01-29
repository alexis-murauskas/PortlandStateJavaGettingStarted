package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.util.Scanner;

public class TextParser<T extends AbstractAirline> implements AirlineParser<T> {
    private String fileName;

    public TextParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T parse() throws ParserException {
        Airline<Flight> airline = null;

        try {
            String[] input;
            File file = new File(this.fileName);
            if (file.createNewFile() == true)
                return (T) airline;

            Scanner reader = new Scanner(file);

            if (reader.hasNextLine()) {
                String name = reader.nextLine();
                airline = new Airline<>(name);
            }

            while (reader.hasNextLine()) {
                String line = "placeholder;" + reader.nextLine();
                input = line.split(";");
                InputModel rv = AirlineCommand.parse(input);
                airline.addFlight(rv);
            }

            reader.close();
        }
        catch (Exception e) {
            throw new ParserException(e.getMessage());
        }

        return (T) airline;
    }
}

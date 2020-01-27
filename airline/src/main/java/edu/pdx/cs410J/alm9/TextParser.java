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
        Airline airline = null;

        try {
            String[] name;
            String[] flight;
            File input = new File(this.fileName);
            Scanner reader = new Scanner(input);

            if (reader.hasNextLine()) {
                String rv = reader.nextLine();
                airline = new Airline(rv);
                name = rv.split(" ");
            }

            while (reader.hasNextLine()) {
                flight = reader.nextLine().split(";");
            }

            reader.close();
        }
        catch (Exception e) {
            throw new ParserException(e.getMessage());
        }

        return (T) airline;
    }
}

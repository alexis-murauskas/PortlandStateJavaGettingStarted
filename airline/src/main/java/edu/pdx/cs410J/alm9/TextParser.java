package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.util.Scanner;

public class TextParser<T extends AbstractAirline> implements AirlineParser<T> {
    public static String fileName = "airline.txt";

    @Override
    public T parse() throws ParserException {
        AirlineController controller = new AirlineController();
        InputModel model = new InputModel();

        try {
            File input = new File(this.fileName);
            Scanner reader = new Scanner(input);
            

            reader.close();
        }
        catch (Exception e) {
            throw new ParserException(e.getMessage());
        }

        controller.create(model);
        return (T) controller.getAirline();
    }
}

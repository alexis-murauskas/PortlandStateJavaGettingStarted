package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.alm9.*;

/**
 * Converts a text file to an XML file using the text parser and XML dumper. If there is an error
 * during either process, the program will exit with a 1 code. This is also true for failing to
 * include the names of the files
 */
public class Converter {

    public static void main(String[] args) {
        if (args.length != 2)
            System.exit(1);

        TextParser<Airline> parser = new TextParser<>(args[0]);
        XmlDumper<Airline<Flight>, Flight> dumper = new XmlDumper<>(args[1]);

        try {
            dumper.dump(parser.parse());
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

        System.exit(0);
    }
}

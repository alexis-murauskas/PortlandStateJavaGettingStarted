package edu.pdx.cs410J.alm9.Converter;

import edu.pdx.cs410J.alm9.Airline;
import edu.pdx.cs410J.alm9.Flight;
import edu.pdx.cs410J.alm9.XmlDumper;
import edu.pdx.cs410J.alm9.XmlParser;

public class Converter {

    public static void main(String[] args) {
        if (args.length != 2)
            System.exit(1);

        XmlParser<Airline<Flight>, Flight> parser = new XmlParser<>(args[0]);
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

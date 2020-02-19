package edu.pdx.cs410J.alm9.Converter;

import edu.pdx.cs410J.alm9.*;

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

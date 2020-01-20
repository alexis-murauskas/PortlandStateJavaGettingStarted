package edu.pdx.cs410J.alm9;

import java.util.ArrayList;

public class AirlineCommand {
    private enum Command {
        AIRLINE,
        FLIGHT,
        SRC,
        DEPART,
        DEST,
        ARRIVE
    }

    private String[] Options = { "README", "PRINT" };

    public static InputModel parse(String input) {
        if (input == null)
            throw new NullPointerException();

        String[] commands = input.split(" ");
        return new InputModel(
                parseOptions(commands),
                parseAirline(commands),
                parseFlight(commands),
                parseSource(commands),
                parseDeparture(commands),
                parseDestination(commands),
                parseArrival(commands)
        );
    }

    private static ArrayList<String> parseOptions(String[] commands) {
        ArrayList<String> options = new ArrayList<>();

        /* for (int i = 0; commands[i].startsWith("-"); i++) {
        } */

        return options;
    }

    private static String parseAirline(String[] commands) {
        return "";
    }

    private static String parseFlight(String[] commands) {
        return "";
    }

    private static String parseSource(String[] commands) {
        return "";
    }

    private static String parseDeparture(String[] commands) {
        return "";
    }

    private static String parseDestination(String[] commands) {
        return "";
    }

    private static String parseArrival(String[] commands) {
        return "";
    }
}

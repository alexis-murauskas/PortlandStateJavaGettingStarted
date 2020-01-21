package edu.pdx.cs410J.alm9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AirlineCommand {
    private static final int AIRLINE = 0;
    
    private enum Arg {
        FLIGHT,
        SRC,
        DEPART,
        DEST,
        ARRIVE
    }

    private static final List<String> validOptions = Arrays.asList(
            "-README",
            "-PRINT"
    );

    public static InputModel parse(String[] input) {
        if (input == null)
            throw new NullPointerException();

        ArrayList<String> options = parseOptions(input);
        String[] arguments = trimArguments(options, input);
        InputModel model =  parseArgs(arguments);
        model.options = options;

        return model;
    }

    private static ArrayList<String> parseOptions(String[] input) {
        ArrayList<String> options = new ArrayList<>();

        for (String option : input) {
            if (option.startsWith("-") && validOptions.contains(option))
                options.add(option);
        }

        return options;
    }

    private static String[] trimArguments(ArrayList<String> toRemove, String[] input) {
        return Arrays.copyOfRange(input, toRemove.size(), input.length);
    }

    private static InputModel parseArgs(String[] input) {
        InputModel model = new InputModel();
        if (input.length == 0)
            return model;

        ArrayList<String> airline = parseAirline(input);
        String[] args = trimArguments(airline, input);

        model.airline = stringifyList(airline);
        model.flightNumber = parseFlight(args);
        model.source = parseAirportCode(args);
        model.departureTime = parseDateTime(args);
        model.destination = parseAirportCode(args);
        model.arrivalTime = parseDateTime(args);

        return model;
    }

    private static ArrayList<String> parseAirline(String[] input) {
        ArrayList<String> airline = new ArrayList<>();
        airline.add(input[AIRLINE]);
        int i;

        if (!airline.get(0).startsWith("\""))
            return airline;

        for (i = 0; i < input.length && !input[i].endsWith("\""); i++)
            airline.add(input[i]);

        airline.add(input[i]);
        return airline;
    }

    private static String stringifyList(ArrayList<String> list) {
        return list.stream().collect(Collectors.joining(" "));
    }

    private static String parseFlight(String[] input) {
        return "";
    }

    private static String parseAirportCode(String[] input) {
        return "";
    }

    private static String parseDateTime(String[] input) {
        return "";
    }
}

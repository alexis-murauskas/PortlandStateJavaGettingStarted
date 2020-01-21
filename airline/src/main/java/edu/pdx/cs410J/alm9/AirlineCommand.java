package edu.pdx.cs410J.alm9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AirlineCommand {
    private static final int AIRLINE = 0;
    private static final int FLIGHT = 0;
    private static final int SRC = 1;
    private static final int DEPART = 2;
    private static final int DEST = 3;
    private static final int ARRIVE = 4;

    private static final int CODELEN = 3;

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
        model.flightNumber = checkFlight(args[FLIGHT]);
        model.source = checkAirportCode(args[SRC]);
        // model.departureTime = checkDateTime(args[DEPART]);
        // model.destination = checkAirportCode(args[DEST]);
        // model.arrivalTime = checkDateTime(args[ARRIVE]);

        return model;
    }

    private static ArrayList<String> parseAirline(String[] input) {
        ArrayList<String> airline = new ArrayList<>();
        airline.add(input[AIRLINE]);
        int i;

        if (!input[AIRLINE].startsWith("'"))
            return airline;

        for (i = AIRLINE+1; i < input.length && !input[i].endsWith("'"); i++)
            airline.add(input[i]);

        airline.add(input[i]);
        return airline;
    }

    private static String stringifyList(ArrayList<String> list) {
        return list.stream().collect(Collectors.joining(" "));
    }

    private static String checkFlight(String input) {
        Integer.parseInt(input);
        return input;
    }

    private static String checkAirportCode(String input) {
        if (input.length() > CODELEN || input.matches("[A-Za-z]]+"))
            throw new IllegalArgumentException();

        return input;
    }

    private static String checkDateTime(String input) {
        return "";
    }
}

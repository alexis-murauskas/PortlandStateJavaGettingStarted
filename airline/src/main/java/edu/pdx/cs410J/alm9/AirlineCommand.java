package edu.pdx.cs410J.alm9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AirlineCommand {
    private enum Arg {
        AIRLINE,
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
        ArrayList<String> airline = parseAirline(arguments);
        arguments = trimArguments(airline, arguments);

        return new InputModel(
                options,
                stringifyList(airline),
                parseFlight(arguments),
                parseSource(arguments),
                parseDeparture(arguments),
                parseDestination(arguments),
                parseArrival(arguments)
        );
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
        if (toRemove.isEmpty())
            return input;

        return Arrays.copyOfRange(input, toRemove.size()-1, input.length);
    }

    private static ArrayList<String> parseAirline(String[] input) {
        ArrayList<String> airline = new ArrayList<>();
        airline.add(input[Arg.AIRLINE.ordinal()]);
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

    private static String parseSource(String[] input) {
        return "";
    }

    private static String parseDeparture(String[] input) {
        return "";
    }

    private static String parseDestination(String[] input) {
        return "";
    }

    private static String parseArrival(String[] input) {
        return "";
    }
}

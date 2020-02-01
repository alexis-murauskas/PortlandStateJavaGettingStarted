package edu.pdx.cs410J.alm9;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AirlineCommand {
    private static final int AIRLINE = 0;
    private static final int FLIGHT = 0;
    private static final int SRC = 1;
    private static final int DEPART = 2;
    private static final int DPTTIME = 3;
    private static final int DEST = 4;
    private static final int ARRIVE = 5;
    private static final int ARVTIME = 6;

    private static final int CODELEN = 3;

    public static final List<String> validOptions = Arrays.asList(
            "-README",
            "-print",
            "-textFile"
    );

    /**
     * Parse is responsible for checking if the user input is complete and correct. If all the input
     * passes checks, then it is organized into an InputModel object that can be consumed by Main and/or
     * the Airline Controller.
     *
     * @param input String array of user input, including options and arguments.
     * @return Returns input organized into a model if all error checks pass.
     * @throws ArrayIndexOutOfBoundsException if something goes wrong in parsing options or Airline Name
     *                                        because of invalid user input
     * @throws IllegalArgumentException       if Airport Code is the incorrect length
     *                                        or contains non-alphabetic characters
     * @throws DateTimeParseException         if the date and/or time are improperly formatted
     * @throws NumberFormatException          if the Flight Code is not numerical
     */
    public static InputModel parse(String[] input) {
        if (input == null)
            throw new NullPointerException();

        ArrayList<String> options = parseOptions(input);
        String[] arguments = trimArguments(options, input);
        InputModel model = parseArgs(arguments);
        model.options = options;

        if (options.contains("-textFile"))
            compareFileName(model);

        return model;
    }

    /**
     * ParseOptions checks for optional flags from the user and adds them if they correctly denoted with
     * a leading hyphen and if they are among the listed valid options.
     *
     * @param input User input that has been forwarded by Parse.
     * @return A list of extracted options to be placed into the InputModel.
     */
    private static ArrayList<String> parseOptions(String[] input) {
        ArrayList<String> options = new ArrayList<>();

        for (var i = 0; i < input.length; i++) {
            if (input[i].contains("-textFile")) {
                options.add(input[i]);
                options.add(input[i + 1]);
            } else if (input[i].startsWith("-") && validOptions.contains(input[i])) {
                options.add(input[i]);
            } else if (input[i].startsWith("-"))
                throw new IllegalArgumentException("Unknown command line option");
        }

        return options;
    }

    /**
     * Trims arguments that have already been evaluated by the parser. This is intended for use with
     * extracting items of variable length, such as options and the Airline Name.
     *
     * @param toRemove A list of already evaluated items.
     * @param input    Current array of user input.
     * @return User input with already evaluated arguments removed.
     */
    private static String[] trimArguments(ArrayList<String> toRemove, String[] input) {
        return Arrays.copyOfRange(input, toRemove.size(), input.length);
    }

    /**
     * ParseArgs checks if flight arguments are present and then evaluates them to be placed into the
     * InputModel.
     *
     * @param input Current array of user input, possibly with options removed.
     * @return If all input items pass checks, an InputModel with the items included will be returned.
     */
    private static InputModel parseArgs(String[] input) {
        InputModel model = new InputModel();
        if (input.length == 0)
            return model;

        ArrayList<String> airline = parseAirline(input);
        String[] args = trimArguments(airline, input);

        model.airline = stringifyList(airline);
        model.flightNumber = checkFlight(args[FLIGHT]);
        model.source = checkAirportCode(args[SRC]);
        model.departureTime = checkDateTime(args[DEPART] + " " + args[DPTTIME]);
        model.destination = checkAirportCode(args[DEST]);
        model.arrivalTime = checkDateTime(args[ARRIVE] + " " + args[ARVTIME]);

        if (args.length > ARVTIME + 1)
            throw new IllegalArgumentException("Unknown command line argument");

        return model;
    }

    /**
     * Checks for an Airline Name, which is either enclosed in quotes or a single word. There are no
     * rules for names, except that the name must be enclosed in quotes if applicable.
     *
     * @param input Current array of user input.
     * @return A list of all strings included in the Airline Name, which may be one string.
     */
    private static ArrayList<String> parseAirline(String[] input) {
        ArrayList<String> airline = new ArrayList<>();
        airline.add(input[AIRLINE]);
        int i;

        if (!input[AIRLINE].startsWith("'"))
            return airline;

        for (i = AIRLINE + 1; i < input.length && !input[i].endsWith("'"); i++)
            airline.add(input[i]);

        airline.add(input[i]);
        return airline;
    }

    /**
     * StringifyList is a helper function for parseAirline that collects all the strings in the name into
     * a single string.
     *
     * @param list A list of strings.
     * @return All strings in the list collected into one string, with words separated by spaces.
     */
    private static String stringifyList(ArrayList<String> list) {
        return list.stream().collect(Collectors.joining(" "));
    }

    /**
     * Attempts to parse a Flight Code as an integer. If it fails, the input is incorrect. Otherwise
     * it simply returns the same string.
     *
     * @param input Input representing a Flight Code.
     * @return The same input string.
     */
    private static String checkFlight(String input) {
        Integer.parseInt(input);
        return input;
    }

    /**
     * Checks an Airport Code to make sure that it only contains letters and has a length of 3.
     *
     * @param input Input representing an Airport Code.
     * @return The same input string.
     */
    private static String checkAirportCode(String input) {
        if (input.length() != CODELEN)
            throw new IllegalArgumentException("Airport code is too short");
        if (input.matches("[A-Za-z]]+"))
            throw new IllegalArgumentException("Airport code has number");

        return input;
    }

    /**
     * Attempts to parse a DateTime string. If it succeeds, then the input has been correctly formatted.
     * Otherwise, it throws an exception.
     *
     * @param input Input representing a date and time.
     * @return The same input string.
     */
    private static String checkDateTime(String input) {
        LocalDateTime.parse(input, Flight.DATEFORMAT);
        return input;
    }

    public static void compareFileName(InputModel model) {
        String airline = null;

        try {
            int fileIndex = model.options.indexOf("-textFile")+1;
            String fileName = model.options.get(fileIndex);
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.hasNextLine())
                airline = reader.nextLine();

            reader.close();

        } catch (Exception e) {
            return;
        }

        if (!airline.equals(model.airline))
            throw new IllegalArgumentException("Airline name does not match file contents");
    }
}

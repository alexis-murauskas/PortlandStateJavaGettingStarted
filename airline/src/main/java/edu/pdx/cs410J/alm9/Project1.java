package edu.pdx.cs410J.alm9;

import java.time.format.DateTimeParseException;

/**
 * The main class for the CS410J airline Project
 * Project 1: main will accept command line arguments that allow the user to create a flight, print a
 * README, and/or print out the flight just added.
 */
public class Project1 {

    protected static String readme = "Alexis Murauskas - CS410J - Winter2020 - Project1\n\n" +
            "usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>\n\n" +
            "args are (in this order):\n" +
            "airline The name of the airline\n" +
            "flightNumber The flight number\n" +
            "src Three-letter code of departure airport\n" +
            "depart Departure date and time (24-hour time)\n" +
            "dest Three-letter code of arrival airport\n" +
            "arrive Arrival date and time (24-hour time)\n\n" +
            "options are (options may appear in any order):\n" +
            "-print Prints a description of the new flight\n" +
            "-README Prints a README for this project and exits\n\n" +
            "Date and time should be in the format: mm/dd/yyyy hh:mm\n";

    public static void main(String[] args) {
        InputModel model = null;

        // Null checking
        if (args == null) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        // Parse the input
        try {
            model = AirlineCommand.parse(args);
        }
        catch (NumberFormatException e) {
            System.err.println("Flight code is not numerical");
            System.exit(1);
        }
        catch(DateTimeParseException e) {
            System.err.println("Time is malformatted");
            System.exit(1);
        }
        catch (IllegalArgumentException e) {
            if(e.getMessage() == "options")
                System.err.println("Unknown command line option");
            else
                System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Arguments could not be parsed");
            System.exit(1);
        }

        // Check for README
        if (model.options.contains("-README")) {
            System.out.println(readme);
            System.exit(0);
        }

        // Add to airline
        try {
            Airline<Flight> airline = new Airline<>(model.airline);
            Flight rv = airline.addFlight(model);
            if (model.options.contains("-print"))
                System.out.println(rv.toString());
        } catch (Exception e) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        System.exit(0);
    }

}
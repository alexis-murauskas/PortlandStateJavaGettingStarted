package edu.pdx.cs410J.alm9;

import java.time.format.DateTimeParseException;

public class Project2 {

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
            "-textFile file Where to read/write the airline info\n" +
            "-print Prints a description of the new flight\n" +
            "-README Prints a README for this project and exits\n\n" +
            "Date and time should be in the format: mm/dd/yyyy hh:mm\n";

    public static void main(String[] args) {
        InputModel model = null;
        AirlineController controller = new AirlineController();

        if (args == null) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        try {
            model = AirlineCommand.parse(args);
        } catch (NumberFormatException e) {
            System.err.println("Flight code is not numerical");
            System.exit(1);
        } catch (DateTimeParseException e) {
            System.err.println("Time is malformatted");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            if (e.getMessage() == "options")
                System.err.println("Unknown command line option");
            else
                System.err.println(e.getMessage());
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Arguments could not be parsed");
            System.exit(1);
        }

        if (model.options.contains("-README")) {
            System.out.println(readme);
            System.exit(0);
        }

        try {
            String added = controller.create(model);
            if (model.options.contains("-print"))
                System.out.println(added);
        } catch (Exception e) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        System.exit(0);
    }
}

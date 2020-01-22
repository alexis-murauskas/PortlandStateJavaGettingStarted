package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

    public static String readme = "usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>\n\n" +
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
            "Date and time should be in the format: mm/dd/yyyy hh:mm";

    public static void main(String[] args) {
        InputModel model = null;
        AirlineController controller = new AirlineController();

        if (args == null) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        try {
            model = AirlineCommand.parse(args);
        } catch (Exception e) {
            System.err.println("Arguments could not be parsed");
            System.exit(1);
        }

        if (model.options.contains("-README")) {
            System.out.println(readme);
            System.exit(0);
        }

        try {
            String added = controller.create(model);
            if (model.options.contains("-PRINT"))
                System.out.println(added);
        } catch (Exception e) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        System.exit(0);
    }

}
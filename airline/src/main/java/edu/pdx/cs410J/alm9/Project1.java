package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

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
            System.out.println("README");
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
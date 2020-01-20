package edu.pdx.cs410J.alm9;

public class AirlineCommand {
    private enum Command {
        AIRLINE,
        FLIGHT,
        SRC,
        DEPART,
        DEST,
        ARRIVE
    }

    public static InputModel parse(String input) {
        if (input == null)
            throw new NullPointerException();

        return new InputModel();
    }

}

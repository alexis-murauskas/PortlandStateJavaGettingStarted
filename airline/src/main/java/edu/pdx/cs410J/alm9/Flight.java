package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight extends AbstractFlight {

    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private static int flightNumber;
    private static String source;
    private static LocalDateTime departureTime;
    private static String destination;
    private static LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(int flightNumber, String source, LocalDateTime departureTime, String destination, LocalDateTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return The flight number.
     */
    @Override
    public int getNumber() {
        return this.flightNumber;
    }

    /**
     * Returns the departure location represented as an Airport Code.
     * @return An airport code.
     */
    @Override
    public String getSource() {
        return this.source;
    }

    /**
     * Returns the date and time of departure.
     * @return Formatted time and date.
     */
    @Override
    public String getDepartureString() {
        if (this.departureTime == null)
            return null;

        return this.departureTime.format(DATEFORMAT);
    }

    /**
     * Returns the Airport Code representing the destination for the flight.
     * @return An airport code.
     */
    @Override
    public String getDestination() {
        return this.destination;
    }

    /**
     * Returns the date and time of the arrival.
     * @return A formatted date and time.
     */
    @Override
    public String getArrivalString() {
        if (this.arrivalTime == null)
            return null;

        return this.arrivalTime.format(DATEFORMAT);
    }

    public static String toFileFormat(Flight flight) {
        return flight.flightNumber
                + ";" + flight.source
                + ";" + flight.departureTime.format(DATEFORMAT).replace(" ", ";")
                + ";" + flight.destination
                + ";" + flight.arrivalTime.format(DATEFORMAT).replace(" ", ";");
    }
}

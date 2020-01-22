package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight extends AbstractFlight {

    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private int flightNumber;
    private String source;
    private LocalDateTime departureTime;
    private String destination;
    private LocalDateTime arrivalTime;

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
}

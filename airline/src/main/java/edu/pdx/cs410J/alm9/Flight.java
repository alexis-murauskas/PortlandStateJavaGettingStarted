package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Flight<T extends AbstractFlight> extends AbstractFlight implements Comparable<T> {

    public static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    public static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("MM/dd/yy KK:mm a");

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

    public Date getDepature() {
        return new Date();
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

    public Date getArrival() {
        return new Date();
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

    @Override
    public int compareTo(T t) {
        return 0;
    }
}

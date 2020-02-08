package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight<T extends AbstractFlight, Q extends Flight> extends AbstractFlight implements AbstractComparable<Q> {

    public static final SimpleDateFormat PARSEFORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
    public static final SimpleDateFormat PRETTYFORMAT = new SimpleDateFormat("MM/dd/yy HH:mm a");

    private int flightNumber;
    private String source;
    private Date departure;
    private String destination;
    private Date arrival;

    public Flight() {
    }

    public Flight(int flightNumber, String source, Date departure, String destination, Date arrival) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
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

    public Date getDeparture() {
        return this.departure;
    }

    /**
     * Returns the date and time of departure.
     * @return Formatted time and date.
     */
    @Override
    public String getDepartureString() {
        if (this.departure == null)
            return null;

        return this.PRETTYFORMAT.format(this.departure);
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
        return this.arrival;
    }

    /**
     * Returns the date and time of the arrival.
     * @return A formatted date and time.
     */
    @Override
    public String getArrivalString() {
        if (this.arrival == null)
            return null;

        return this.PRETTYFORMAT.format(this.arrival);
    }

    /**
     * Compares two AbstractFlight objects first based on the source airport, and then on
     * the departure time if the codes are the same.
     * @param q the object to compare to
     * @return 0 indicates the two values are equal, >0 indicates that the calling object is "greater,"
     * and <0 otherwise.
     */
    @Override
    public int compareTo(Q q) {
        int rv = this.source.compareToIgnoreCase(q.getSource());

        if (rv == 0)
            rv = this.departure.compareTo(q.getDeparture());

        return rv;
    }
}

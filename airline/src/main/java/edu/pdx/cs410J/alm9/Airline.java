package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Collection;
import java.util.HashSet;

public class Airline<T extends AbstractFlight> extends AbstractAirline<T> {
    private String name;
    private Collection<T> flights = new HashSet<T>();

    public Airline() {
    }

    /**
     * Sets the name of the Airline.
     * @param name An airline name.
     */
    public Airline(String name) {
        this.name = name;
    }

    /**
     * @return The name of the airline.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Adds a new flight to the airline.
     * @param flight A generic object that represents a flight.
     */
    @Override
    public void addFlight(T flight) {
        this.flights.add(flight);
    }

    /**
     * Retrieves all the saved flights for the airline.
     * @return A collection of flights.
     */
    @Override
    public Collection<T> getFlights() {
        return this.flights;
    }
}

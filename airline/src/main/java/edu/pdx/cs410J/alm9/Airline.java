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

    public Airline(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFlight(T flight) {
        this.flights.add(flight);
    }

    @Override
    public Collection<T> getFlights() {
        return this.flights;
    }
}

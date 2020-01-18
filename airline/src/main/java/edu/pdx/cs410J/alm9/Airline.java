package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Collection;

public class Airline extends AbstractAirline {
    private String name;
    private Collection<Flight> flights;

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
    public void addFlight(AbstractFlight abstractFlight) {
        this.flights.add((Flight) abstractFlight);
    }

    @Override
    public Collection getFlights() {
        return this.flights;
    }
}

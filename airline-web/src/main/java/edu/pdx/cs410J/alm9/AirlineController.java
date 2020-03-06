package edu.pdx.cs410J.alm9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirlineController {
    private List<Airline<Flight>> airlines;

    public AirlineController() {
        airlines = new ArrayList<>();
    }

    public void addAirline(Airline airline) {
        airlines.add(airline);
    }

    public Airline<Flight> findAirline(String name) {
        if (airlines.isEmpty())
            return null;

        var rv = airlines.stream()
                .filter(a -> a.getName().equals(name))
                .findAny();

        if (rv.isPresent())
            return rv.get();

        return null;
    }

    public Airline<Flight> findAirline(String name, String src, String dest) {
        if (airlines.isEmpty())
            return null;

        var airline = airlines.stream()
                .filter(a -> a.getName().equals(name))
                .findAny();

        if (!airline.isPresent())
            return null;

        var found = airline.get();
        var flights = found
                .getFlights()
                .stream()
                .filter(f -> f.getSource().equals(src) && f.getDestination().equals(dest))
                .collect(Collectors.toList());

        var rv = new Airline<Flight>(name);
        for (var flight : flights)
            rv.addFlight(flight);

        return rv;
    }
}

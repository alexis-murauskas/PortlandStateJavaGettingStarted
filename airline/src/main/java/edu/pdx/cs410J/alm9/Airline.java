package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class Airline<T extends AbstractFlight> extends AbstractAirline<T> {
    private String name;
    private Collection<T> flights = new ArrayList<T>();

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
     * Adds a new flight to the airline.
     * @param model A generic object that represents a flight.
     */
    public T addFlight(InputModel model) throws ParseException {
        Flight flight = new Flight(
                Integer.parseInt(model.flightNumber),
                model.source,
                Flight.PARSEFORMAT.parse(model.departureTime),
                model.destination,
                Flight.PARSEFORMAT.parse(model.arrivalTime)
        );

        this.flights.add((T) flight);

        return (T) flight;
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

package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractAirline;

import java.time.LocalDateTime;

public class AirlineController {
    private Airline airline;

    public AirlineController() {
        this.airline = new Airline<Flight>();
    }

    public AirlineController(String airline) {
        this.airline = new Airline<Flight>(airline);
    }

    /**
     * Create makes a new model based on the provided InputModel.
     *
     * @param model Represents correct user input that can be read to create a Flight.
     * @throws NullPointerException if input is null
     * @return The new Flight represented as a string.
     */
    public String create(InputModel model) {
        if (model == null)
            throw new NullPointerException();

        Flight flight = new Flight(
                Integer.parseInt(model.flightNumber),
                model.source,
                LocalDateTime.parse(model.departureTime, Flight.DATEFORMAT),
                model.destination,
                LocalDateTime.parse(model.arrivalTime, Flight.DATEFORMAT)
        );

        this.airline.addFlight(flight);
        return flight.toString();
    }

    public AbstractAirline getAirline() {
        final Airline airline = this.airline;
        return airline;
    }
}

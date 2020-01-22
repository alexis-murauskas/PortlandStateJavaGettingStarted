package edu.pdx.cs410J.alm9;

import java.time.LocalDateTime;

public class AirlineController {
    private Airline airline;

    public AirlineController() {
    }

    /**
     * Create makes a new flight based on the provided InputModel.
     *
     * @param airline Represents correct user input that can be read to create a Flight.
     * @throws NullPointerException if input is null
     * @return The new Flight represented as a string.
     */
    public String create(InputModel airline) {
        if (airline == null)
            throw new NullPointerException();

        this.airline = new Airline<Flight>(airline.airline);

        Flight flight = new Flight(
                Integer.parseInt(airline.flightNumber),
                airline.source,
                LocalDateTime.parse(airline.departureTime, Flight.DATEFORMAT),
                airline.destination,
                LocalDateTime.parse(airline.arrivalTime, Flight.DATEFORMAT)
        );

        this.airline.addFlight(flight);
        return flight.toString();
    }
}

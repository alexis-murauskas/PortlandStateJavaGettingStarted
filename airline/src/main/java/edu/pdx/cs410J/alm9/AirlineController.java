package edu.pdx.cs410J.alm9;

import java.time.LocalDateTime;

public class AirlineController {
    private Airline airline;

    public AirlineController() {
    }

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

package edu.pdx.cs410J.alm9;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class AirlineController {
    private Airline airline;

    public AirlineController() {
    }

    public String Create(InputModel airline) {
        if (airline == null)
            throw new NullPointerException();

        this.airline = new Airline(airline.airline);
        Flight flight = new Flight(
                Integer.parseInt(airline.flightNumber),
                airline.source,
                LocalDateTime.parse(airline.departureTime, Flight.DATEFORMAT),
                airline.destination,
                LocalDateTime.parse(airline.arrivalTime, Flight.DATEFORMAT)
        );

        return "";
    }
}

package edu.pdx.cs410J.alm9;

public class InputModel {
    public String airline;
    public String flightNumber;
    public String source;
    public String departureTime;
    public String destination;
    public String arrivalTime;

    public InputModel() {
    }

    public InputModel(String airline, String flightNumber, String source, String departureTime, String destination, String arrivalTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.source = source;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
    }
}

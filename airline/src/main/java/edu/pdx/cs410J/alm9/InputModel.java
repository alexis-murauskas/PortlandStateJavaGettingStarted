package edu.pdx.cs410J.alm9;

import java.util.ArrayList;

public class InputModel {
    public ArrayList<String> options;
    public String airline;
    public String flightNumber;
    public String source;
    public String departureTime;
    public String destination;
    public String arrivalTime;

    public InputModel() {
    }

    public InputModel(ArrayList<String> options, String airline, String flightNumber, String source, String departureTime, String destination, String arrivalTime) {
        this.options = options;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.source = source;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
    }
}

package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;
import java.time.LocalDateTime;

public class Flight extends AbstractFlight {

  private int flightNumber;
  private String source;
  private LocalDateTime departureTime;
  private String destination;
  private LocalDateTime arrivalTime;

  public Flight() {}

  public Flight(int flightNumber, String source, LocalDateTime departureTime, String destination, LocalDateTime arrivalTime) {
    this.flightNumber = flightNumber;
    this.source = source;
    this.departureTime = departureTime;
    this.destination = destination;
    this.arrivalTime = arrivalTime;
  }

  @Override
  public int getNumber() {
    return this.flightNumber;
  }

  @Override
  public String getSource() { return this.source; }

  @Override
  public String getDepartureString() { return this.departureTime.toString(); }

  @Override
  public String getDestination() { return this.destination; }

  @Override
  public String getArrivalString() {
    return this.arrivalTime.toString();
  }
}

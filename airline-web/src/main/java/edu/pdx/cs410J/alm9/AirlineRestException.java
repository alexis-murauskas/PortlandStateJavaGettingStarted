package edu.pdx.cs410J.alm9;

public class AirlineRestException extends RuntimeException {
    AirlineRestException(String message) {
        super("Got an HTTP Status Code of " + message);
    }
}

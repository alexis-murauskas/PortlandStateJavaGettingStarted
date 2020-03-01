package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class PrettyPrinterTest {

    private SimpleDateFormat formatter = Flight.PARSEFORMAT;
    private Flight flight1 = new Flight(
            1,
            "PDX",
            formatter.parse("02/04/2020 11:00 AM"),
            "ABQ",
            formatter.parse("02/04/2020 02:00 PM")
    );

    private Flight flight2 = new Flight(
            2,
            "PDX",
            formatter.parse("02/05/2020 11:00 AM"),
            "ABQ",
            formatter.parse("02/05/2020 3:00 PM")
    );

    private Flight flight3 = new Flight(
            3,
            "PDX",
            formatter.parse("02/06/2020 11:00 AM"),
            "ABQ",
            formatter.parse("02/06/2020 4:43 PM")
    );

    public PrettyPrinterTest() throws ParseException {
    }

    @Test (expected = IOException.class)
    public void passingNullAirlineThrowsException() throws IOException {
        PrettyPrinter<Airline<Flight>, Flight> printer = new PrettyPrinter<>("-");
        printer.dump(null);
    }

    @Test (expected = IOException.class)
    public void emptyFileNameThrowsException() throws IOException {
        Airline<Flight> airline = new Airline<>();
        PrettyPrinter<Airline<Flight>, Flight> printer = new PrettyPrinter<>("");
        printer.dump(airline);
    }

    @Test
    public void validAirlineIsPrintedToScreen() throws ParseException, IOException {
        Airline<Flight> airline = new Airline<>("'Airline Name'");
        airline.addFlight(flight1);
        airline.addFlight(flight2);
        airline.addFlight(flight3);

        PrettyPrinter<Airline<Flight>, Flight> printer = new PrettyPrinter<>("-");
        printer.dump(airline);
    }

    @Test
    public void validAirlineIsPrintedToFile() throws ParseException, IOException {
        Airline<Flight> airline = new Airline<>("'Airline Name'");
        airline.addFlight(flight1);
        airline.addFlight(flight2);
        airline.addFlight(flight3);

        PrettyPrinter<Airline<Flight>, Flight> printer = new PrettyPrinter<>("pretty.txt");
        printer.dump(airline);
        File file = new File("pretty.txt");
        assertThat(file.isFile(), is(true));
    }

}

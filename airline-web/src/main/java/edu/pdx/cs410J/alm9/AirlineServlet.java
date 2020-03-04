package edu.pdx.cs410J.alm9;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.web.HttpRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>Airline</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class AirlineServlet extends HttpServlet {
    public final static String AIRLINE = "airline";
    public final static String FLIGHT_NO = "flight_number";
    public final static String SRC = "src";
    public final static String DEPART = "depart";
    public final static String DEST = "dest";
    public final static String ARRIVE = "arrive";

    private List<Airline<Flight>> airlines = new ArrayList<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        var airline = getParameter(AIRLINE, request);
        var src = getParameter(SRC, request);
        var dest = getParameter(DEST, request);

        if (airline == null) {
            missingRequiredParameter(response, AIRLINE);
            return;
        }

        try {
            var found = findAirline(airline);

            if (found != null && src != null && dest != null) {
                var flights = found.getFlights().stream()
                        .filter(f -> f.getSource().equals(src) && f.getDestination().equals(dest))
                        .collect(Collectors.toList());

                found = new Airline<>(airline);
                for (var flight : flights) {
                    found.addFlight(flight);
                }
            }

            dumpAirline(found, response);
        }
        catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        var airline = getParameter(AIRLINE, request);
        var flightNo = getParameter(FLIGHT_NO, request);
        var src = getParameter(SRC, request);
        var depart = getParameter(DEPART, request);
        var dest = getParameter(DEST, request);
        var arrive = getParameter(ARRIVE, request);
        SimpleDateFormat formatter = Flight.PARSEFORMAT;

        try {
            var found = findAirline(airline);
            if (found == null)
                found = new Airline<>(airline);

            var toAdd = new Flight(
                    Integer.parseInt(flightNo),
                    src,
                    formatter.parse(depart),
                    dest,
                    formatter.parse(arrive)
            );

            found.addFlight(toAdd);

            if (!airlines.contains(found))
                airlines.add(found);

            dumpAirline(found, response);
        }
        catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private Airline<Flight> findAirline(String name) {
        var rv = airlines.stream()
                .filter(a -> a.getName().equals(name))
                .findAny();

        if (rv.isPresent())
            return rv.get();

        return null;
    }

    private void dumpAirline(Airline<Flight> airline, HttpServletResponse response) throws IOException {
        var pw = response.getWriter();
        XmlDumper<Airline<Flight>, Flight> dumper = new XmlDumper<>("response.xml");

        dumper.dump(airline);
        var message = new String(Files.readAllBytes(Paths.get("response.xml")));
        pw.println(message);
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        PrintWriter pw = response.getWriter();
        pw.println("Delete functionality not available");
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     * <p>
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter(HttpServletResponse response, String parameterName)
            throws IOException {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     * <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;

        } else {
            return value;
        }
    }
}

package edu.pdx.cs410J.alm9;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AirlineServletTest {

    private Airline<Flight> airline;

    public AirlineServletTest() throws ParseException {
        SimpleDateFormat formatter = Flight.PARSEFORMAT;
        airline = new Airline("'Airline Name'");
        Flight flight1 = new Flight(
                1,
                "PDX",
                formatter.parse("02/04/2020 11:00 AM"),
                "ABQ",
                formatter.parse("02/04/2020 2:00 PM")
        );

        Flight flight2 = new Flight(
                2,
                "BOI",
                formatter.parse("02/05/2020 11:00 AM"),
                "ABQ",
                formatter.parse("02/05/2020 3:00 PM")
        );

        Flight flight3 = new Flight(
                3,
                "PDX",
                formatter.parse("02/06/2020 11:00 AM"),
                "ABQ",
                formatter.parse("02/06/2020 4:43 PM")
        );

        airline.addFlight(flight1);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
    }

    @Test
    public void canCreateServlet() {
        new AirlineServlet();
    }

    @Test
    public void emptyServerReturns404() throws ServletException, IOException {
        AirlineServlet servlet = new AirlineServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);

        when(request.getParameter("airline")).thenReturn("DoesNotExist");
        when(response.getWriter()).thenReturn(pw);

        servlet.doGet(request, response);
        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void searchRequestWithoutParametersFails() throws ServletException, IOException {
        AirlineServlet servlet = new AirlineServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter("airline"));
    }

    @Test
    public void postRequestWithoutParametersFails() throws IOException, ServletException {
        AirlineServlet servlet = new AirlineServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);

        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void canSearchWithAirlineSpace() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AirlineController controller = mock(AirlineController.class);
        PrintWriter pw = mock(PrintWriter.class);

        when(request.getParameter("airline")).thenReturn(airline.getName());
        when(controller.findAirline(airline.getName())).thenReturn(airline);
        when(response.getWriter()).thenReturn(pw);

        AirlineServlet servlet = new AirlineServlet(controller);
        servlet.doGet(request, response);
        File file = new File("response.xml");
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(file.isFile(), is(true));
    }

    @Test
    public void canSearchForSpecificFlights() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AirlineController controller = mock(AirlineController.class);
        PrintWriter pw = mock(PrintWriter.class);

        when(request.getParameter("airline")).thenReturn(airline.getName());
        when(request.getParameter("src")).thenReturn("PDX");
        when(request.getParameter("dest")).thenReturn("ABQ");
        when(controller.findAirline(airline.getName(), "PDX", "ABQ")).thenReturn(airline);
        when(response.getWriter()).thenReturn(pw);

        AirlineServlet servlet = new AirlineServlet(controller);
        servlet.doGet(request, response);
        File file = new File("response.xml");
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(file.isFile(), is(true));
    }

    @Test
    public void canPostAirline() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AirlineController controller = mock(AirlineController.class);
        PrintWriter pw = mock(PrintWriter.class);

        when(request.getParameter("airline")).thenReturn(airline.getName());
        when(request.getParameter("flight_number")).thenReturn("1");
        when(request.getParameter("src")).thenReturn("PDX");
        when(request.getParameter("depart")).thenReturn("02/04/2020 11:00 AM");
        when(request.getParameter("dest")).thenReturn("ABQ");
        when(request.getParameter("arrive")).thenReturn("02/04/2020 2:00 PM");
        when(controller.findAirline(airline.getName(), "PDX", "ABQ")).thenReturn(airline);
        when(response.getWriter()).thenReturn(pw);

        AirlineServlet servlet = new AirlineServlet(controller);
        servlet.doPost(request, response);
        File file = new File("response.xml");
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertThat(file.isFile(), is(true));
    }

}

package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project5IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    /**
     * Invokes the main method of {@link Project5} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project5.class, args);
    }

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project5.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project5.MISSING_ARGS));
    }

    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project5.class, "-host", HOSTNAME, "-port", PORT );
        assertThat(result.getExitCode(), equalTo(1));
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */

    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testReadMeOption() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "-README");
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void testReadMeOptionWithArgs() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "-README",
                "Airline",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void testBadlyFormedOptions() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "-badOption",
                "'Airline",
                "Name'",
                "1",
                "PDX",
                "11/11/",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void testGoodInputWithoutOptions() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "Airline",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void testGoodInputWithOptions() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "-print",
                "Airline",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void testBadlyFormedAirlineName() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "'Airline",
                "Name",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Arguments could not be parsed"));
    }

    @Test
    public void testBadlyFormedFlightCode() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "-print",
                "'Airline",
                "Name'",
                "NUMBER",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Flight code isn't an integer"));
    }

    @Test
    public void testBadlyFormedSourceCode() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "'Airline",
                "Name'",
                "1",
                "Sr",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Airport code is too short"));
    }

    @Test
    public void testBadlyFormedDepartureTime() {
        MainMethodResult result = invokeMain(
                "-host",
                "localhost",
                "-port",
                "8080",
                "'Airline",
                "Name'",
                "1",
                "PDX",
                "11/11/",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM"
        );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Time is malformatted"));
    }
}
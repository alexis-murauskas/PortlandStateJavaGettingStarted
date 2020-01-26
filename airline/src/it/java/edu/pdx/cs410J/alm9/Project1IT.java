package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
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
      MainMethodResult result = invokeMain("-readme");
      assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void testReadMeOptionWithArgs() {
    MainMethodResult result = invokeMain(
            "-README",
            "Airline",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
    public void testBadlyFormedOptions() {
      MainMethodResult result = invokeMain(
              "-badOption",
              "'Airline",
              "Name'",
              "1",
              "Src",
              "11/11/",
              "1:11",
              "Dst",
              "12/12/1212",
              "12:12");
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("Arguments could not be parsed"));
  }

  @Test
  public void testGoodInputWithoutOptions() {
    MainMethodResult result = invokeMain(
            "Airline",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test public void testGoodInputWithOptions() {
    MainMethodResult result = invokeMain(
            "-print",
            "Airline",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void testBadlyFormedAirlineName() {
    MainMethodResult result = invokeMain(
            "'Airline",
            "Name",
            "1",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arguments could not be parsed"));
  }

  @Test
  public void testBadlyFormedFlightCode() {
    MainMethodResult result = invokeMain(
            "-print",
            "'Airline",
            "Name'",
            "NUMBER",
            "Src",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arguments could not be parsed"));
  }

  @Test
  public void testBadlyFormedSourceCode() {
    MainMethodResult result = invokeMain(
            "'Airline",
            "Name'",
            "1",
            "Src1234",
            "11/11/1111",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arguments could not be parsed"));
  }

  @Test
  public void testBadlyFormedDepartureTime() {
    MainMethodResult result = invokeMain(
            "'Airline",
            "Name'",
            "1",
            "Src1234",
            "11/11/",
            "11:11",
            "Dst",
            "12/12/1212",
            "12:12");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Arguments could not be parsed"));
  }


  }
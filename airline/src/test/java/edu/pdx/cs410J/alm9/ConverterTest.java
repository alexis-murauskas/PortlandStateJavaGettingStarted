package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.alm9.Converter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConverterTest extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Converter} with the given arguments.
     */
    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return invokeMain(Converter.class, args);
    }
    public static String prefix = "src/test/resources/edu/pdx/cs410J/alm9/";

    @Test
    public void callMain() {
        var rv = invokeMain(prefix+"parser-airline.txt", "airline-converter-out.xml");
        assertThat(rv.getExitCode(), is(0));
    }

    @Test
    public void callMainFails() {
        var rv = invokeMain(prefix+"airline.txt");
        assertThat(rv.getExitCode(), is(1));
    }
}

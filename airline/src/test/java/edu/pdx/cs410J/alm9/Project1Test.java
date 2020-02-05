package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project1Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project1.class, args);
    }

    @Test
    public void checkReadme() {
        assertThat(Project1.readme.contains("Alexis Murauskas"), is(true));
    }

    @Test
    public void callMain() {
        var rv = invokeMain("-print",
                "Airline",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "ABQ",
                "12/12/1212",
                "12:12");
        assertThat(rv.getExitCode(), is(0));
    }
}

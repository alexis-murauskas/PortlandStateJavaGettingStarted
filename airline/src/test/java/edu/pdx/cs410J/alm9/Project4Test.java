package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project4Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project4} with the given arguments.
     */
    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return invokeMain(Project4.class, args);
    }

    @Test
    public void checkReadme() {
        assertThat(Project4.readme.contains("-pretty"), is(true));
    }

    @Test
    public void callMain() {
        var rv = invokeMain("-print",
                "-pretty",
                "mainpretty.txt",
                "Airline",
                "1",
                "PDX",
                "11/11/1111",
                "11:11",
                "AM",
                "ABQ",
                "12/12/1212",
                "12:12",
                "PM");
        assertThat(rv.getExitCode(), is(0));
    }
}

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
        var rv = invokeMain(new String[]{"-README"});
        assertThat(rv.getExitCode(), is(0));
    }
}

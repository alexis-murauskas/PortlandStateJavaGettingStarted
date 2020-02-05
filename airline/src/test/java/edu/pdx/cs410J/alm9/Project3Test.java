package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project3Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return invokeMain(Project3.class, args);
    }

    @Test
    public void checkReadme() {
        assertThat(Project3.readme.contains("-pretty"), is(true));
    }

    @Test
    public void callMain() {
        var rv = invokeMain(new String[]{"-README"});
        assertThat(rv.getExitCode(), is(0));
    }
}

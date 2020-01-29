package edu.pdx.cs410J.alm9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project2Test {
    @Test
    public void callMain() {
        assertThat(Project2.readme,  containsString("Alexis Murauskas"));
    }
}

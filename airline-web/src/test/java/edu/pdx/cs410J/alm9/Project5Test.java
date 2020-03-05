package edu.pdx.cs410J.alm9;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Project5Test {
    @Test
    public void readmeIsCorrect() {
        assertThat(Project5.readme.contains("Alexis Murauskas"), is(true));
    }
}

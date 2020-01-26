package edu.pdx.cs410J.alm9;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class Project1Test {

    @Test
    public void callMain() {
        assertThat(Project1.readme,  containsString("Alexis Murauskas"));
    }
}

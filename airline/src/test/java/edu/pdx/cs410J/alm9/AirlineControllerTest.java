package edu.pdx.cs410J.alm9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AirlineController} class.
 */

public class AirlineControllerTest {

    @Test(expected = NullPointerException.class)
    public void createFailsIfSentNullInput() {
        AirlineController controller = new AirlineController();
        controller.create(null);
    }

    @Test
    public void createSucceedsIfSentInput() {
        InputModel input = new InputModel(
                "AIRLINE",
                "1",
                "SRC",
                "11/11/1111 11:11",
                "DST",
                "12/12/1212 12:12"
        );

        AirlineController controller = new AirlineController();
        controller.create(input);
    }

    @Test
    public void createReturnsNonEmptyString() {
        InputModel input = new InputModel(
                "AIRLINE",
                "1",
                "SRC",
                "11/11/1111 11:11",
                "DST",
                "12/12/1212 12:12"
        );

        AirlineController controller = new AirlineController();
        assertThat(controller.create(input) != "", is(true));
    }
}

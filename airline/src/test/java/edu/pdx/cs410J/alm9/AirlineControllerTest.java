package edu.pdx.cs410J.alm9;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Unit tests for the {@link AirlineController} class.
 */

public class AirlineControllerTest {

    @Test(expected = NullPointerException.class)
    public void createFailsIfSentNullInput() {
        AirlineController controller = new AirlineController();
        controller.Create(null);
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
        controller.Create(input);
    }
}

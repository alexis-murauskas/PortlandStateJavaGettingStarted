package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;

import java.util.Comparator;

public class FlightComparator<T extends AbstractComparable> implements Comparator<T> {
    @Override
    public int compare(T t, T t1) {
        return t.compareTo((Flight) t1);
    }
}

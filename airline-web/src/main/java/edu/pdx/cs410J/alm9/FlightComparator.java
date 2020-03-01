package edu.pdx.cs410J.alm9;

import java.util.Comparator;

/**
 * Used in sorting the flights in the Airline class when calling the stream().sorted() method.
 * @param <T> a flight or derived class.
 */
public class FlightComparator<T extends AbstractComparable> implements Comparator<T> {
    @Override
    public int compare(T t, T t1) {
        return t.compareTo((Flight) t1);
    }
}

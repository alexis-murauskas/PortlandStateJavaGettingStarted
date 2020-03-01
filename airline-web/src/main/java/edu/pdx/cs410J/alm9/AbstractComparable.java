package edu.pdx.cs410J.alm9;

/**
 * Used for sorting flights
 * @param <T> a Flight, or derived class
 */
public interface AbstractComparable<T extends Flight> extends Comparable<T> {
    int compareTo(T t);
}

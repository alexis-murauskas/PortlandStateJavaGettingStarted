package edu.pdx.cs410J.alm9;

import edu.pdx.cs410J.AbstractFlight;

import java.util.Comparator;

public interface AbstractComparable<T extends Flight> extends Comparable<T> {
    int compareTo(T t);
}

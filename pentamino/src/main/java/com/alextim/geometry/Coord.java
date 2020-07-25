package com.alextim.geometry;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@RequiredArgsConstructor @EqualsAndHashCode @ToString
public class Coord implements Comparable<Coord> {

    public final int x;
    public final int y;

    @Override
    public int compareTo(Coord coord) {
        int compare = Comparator.comparingInt((Coord c) -> c.x).compare(this, coord);
        if(compare == 0)
            return Comparator.comparingInt((Coord c) -> c.y).compare(this, coord);
        return compare;
    }
}

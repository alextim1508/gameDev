package com.alextim.geometry;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Figure {

    private List<Coord> coords;

    public List<Coord> getCoords() {
        Collections.sort(coords);
        return coords;
    }

    public void rotate() {
        coords = coords.stream().map((Coord coord) -> new Coord(coord.y,-1*coord.x)).collect(Collectors.toList());
        shift(getMinXYAsCoord());
    }

    public void flip() {
        coords = coords.stream().map((Coord coord) -> new Coord(-1*coord.x , coord.y)).collect(Collectors.toList());
        shift(getMinXYAsCoord());
    }

    private void shift(Coord shiftCoord) {
        coords = coords.stream().map((Coord coord) -> new Coord(coord.x + Math.abs(shiftCoord.x), coord.y + Math.abs(shiftCoord.y))).collect(Collectors.toList());
    }

    public Set<List<Coord>> getAllPosition() {
        Set<List<Coord>> figurePosition = new HashSet<>();

        for (int i = 0; i < 2; i++) {
            figurePosition.add(getCoords());

            for (int j = 0; j < 3; j++) {
                rotate();
                figurePosition.add(getCoords());
            }

            flip();
        }

        return figurePosition;
    }

    private Coord getMinXYAsCoord() {
        AtomicInteger x = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger y = new AtomicInteger(Integer.MAX_VALUE);

        coords.forEach((Coord coord) -> {
            if(coord.x < x.get())
                x.set(coord.x);
            if(coord.y < y.get())
                y.set(coord.y);
        });
        return new Coord(x.get(), y.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return ((Figure) o).getAllPosition().contains(getCoords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords);
    }

    public static List<Coord> toCoords(List<String> s) {
        List<Coord> coords = new ArrayList<>();
        for (int i =0; i< s.size(); i++) {
            for (int j = 0; j < s.get(i).length(); j++) {
                if(s.get(i).charAt(j) != ' ') {
                    coords.add(new Coord(j, i));
                }
            }
        }
        return coords;
    }

    public static List<String> toStrings(List<Coord> coords, int maxX, int maxY) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < maxY; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < maxX; j++) {
                builder.append(coords.contains(new Coord(j, i)) ? '#' : ' ');
            }
            strings.add(builder.toString());
        }
        return strings;
    }

}




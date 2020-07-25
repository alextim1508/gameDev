package com.alextim.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetAllPositionFigureTest {

    @Test
    public void test1() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                " #   ",
                "##   ",
                "#    ",
                "#    ",
                "     "
        )));

        Assertions.assertEquals(8, figure.getAllPosition().size());

        List<Coord> coords = Figure.toCoords(Arrays.asList(
                            " #   ",
                            "##   ",
                            "#    ",
                            "#    ",
                            "     "
        ));
        Collections.sort(coords);

        Assertions.assertTrue(figure.getAllPosition().contains(coords));
        coords = Figure.toCoords(Arrays.asList(
                        "##   ",
                        " ### ",
                        "     ",
                        "     ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                        " #   ",
                        " #   ",
                        "##   ",
                        "#    ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                        "###  ",
                        "  ## ",
                        "     ",
                        "     ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                        "#    ",
                        "##   ",
                        " #   ",
                        " #   ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                        " ### ",
                        "##   ",
                        "     ",
                        "     ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                        "#    ",
                        "#    ",
                        "##   ",
                        " #   ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                        "  ## ",
                        "###  ",
                        "     ",
                        "     ",
                        "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));
    }

    @Test
    public void test2() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                "#    ",
                "#    ",
                "#    ",
                "#    ",
                "#    "
        )));

        Assertions.assertEquals(2, figure.getAllPosition().size());

        List<Coord> coords = Figure.toCoords(Arrays.asList(
                "#    ",
                "#    ",
                "#    ",
                "#    ",
                "#    "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                "#####",
                "     ",
                "     ",
                "     ",
                "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));
    }

    @Test
    public void test3() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                "###  ",
                " #   ",
                " #   ",
                "     ",
                "     "
        )));

        Assertions.assertEquals(4,figure.getAllPosition().size());

        List<Coord> coords = Figure.toCoords(Arrays.asList(
                "###  ",
                " #   ",
                " #   ",
                "     ",
                "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                "#    ",
                "###  ",
                "#    ",
                "     ",
                "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                " #   ",
                " #   ",
                "###  ",
                "     ",
                "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

        coords = Figure.toCoords(Arrays.asList(
                "  #  ",
                "###  ",
                "  #  ",
                "     ",
                "     "
        ));
        Collections.sort(coords);
        Assertions.assertTrue(figure.getAllPosition().contains(coords));

    }
}

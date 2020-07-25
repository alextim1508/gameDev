package com.alextim.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FigureTest {

    @Test
    public void CoordTest() {
        List<Coord> coords = Figure.toCoords(Arrays.asList(
                "##   ",
                "#    ",
                "##   ",
                "     ",
                "     "
        ));
        Assertions.assertEquals(5, coords.size());

        Assertions.assertTrue(coords.containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(0,1),
                new Coord(0,2),
                new Coord(1,2)
                )));
    }

    @Test
    public void equalsFigureTest1() {
        Figure figure1 = new Figure(Figure.toCoords(Arrays.asList(
                "##   ",
                "#    ",
                "##   ",
                "     ",
                "     "
        )));

        Figure figure2 = new Figure(Figure.toCoords(Arrays.asList(
                "# #  ",
                "###  ",
                "     ",
                "     ",
                "     "
        )));

        Assertions.assertTrue(figure1.equals(figure2));
    }

    @Test
    public void equalsFigureTest2() {
        Figure figure1 = new Figure(Figure.toCoords(Arrays.asList(
                "#    ",
                "###  ",
                " #   ",
                "     ",
                "     "
        )));

        Figure figure2 = new Figure(Figure.toCoords(Arrays.asList(
                " ##  ",
                "##   ",
                " #   ",
                "     ",
                "     "
        )));

        Assertions.assertTrue(figure1.equals(figure2));
    }

    @Test
    public void rotateTest1() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
            "##   ",
            "#    ",
            "##   ",
            "     ",
            "     "
        )));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(2,0),
                new Coord(0,1),
                new Coord(1,1),
                new Coord(2,1)
        )));
        Assertions.assertEquals(Arrays.asList(
                "# #  ",
                "###  ",
                "     ",
                "     ",
                "     "
                ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(1,1),
                new Coord(0,2),
                new Coord(1,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                "##   ",
                " #   ",
                "##   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(2,0),
                new Coord(0,1),
                new Coord(2,1)
        )));
        Assertions.assertEquals(Arrays.asList(
                "###  ",
                "# #  ",
                "     ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(0,1),
                new Coord(0,2),
                new Coord(1,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                "##   ",
                "#    ",
                "##   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

    @Test
    public void rotateTest2() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                " ##  ",
                "##   ",
                " #   ",
                "     ",
                "     "
        )));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(0,1),
                new Coord(1,1),
                new Coord(2,1),
                new Coord(1,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                "#    ",
                "###  ",
                " #   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(1,0),
                new Coord(1,1),
                new Coord(2,1),
                new Coord(0,2),
                new Coord(1,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                " #   ",
                " ##  ",
                "##   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(1,0),
                new Coord(0,1),
                new Coord(1,1),
                new Coord(2,1),
                new Coord(2,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                " #   ",
                "###  ",
                "  #  ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(1,0),
                new Coord(2,0),
                new Coord(0,1),
                new Coord(1,1),
                new Coord(1,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                " ##  ",
                "##   ",
                " #   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

    @Test
    public void rotateTest3() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                "#    ",
                "#    ",
                "#    ",
                "#    ",
                "#    "
        )));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(2,0),
                new Coord(3,0),
                new Coord(4,0)
        )));
        Assertions.assertEquals(Arrays.asList(
                "#####",
                "     ",
                "     ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(0,1),
                new Coord(0,2),
                new Coord(0,3),
                new Coord(0,4)
        )));
        Assertions.assertEquals(Arrays.asList(
                "#    ",
                "#    ",
                "#    ",
                "#    ",
                "#    "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

    @Test
    public void rotateTest4() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                "#    ",
                "#    ",
                "#    ",
                "##   ",
                "     "
        )));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(3,0),
                new Coord(0,1),
                new Coord(1,1),
                new Coord(2,1),
                new Coord(3,1)
        )));
        Assertions.assertEquals(Arrays.asList(
                "   # ",
                "#### ",
                "     ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(1,1),
                new Coord(1,2),
                new Coord(1,3)
        )));
        Assertions.assertEquals(Arrays.asList(
                "##   ",
                " #   ",
                " #   ",
                " #   ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(1,0),
                new Coord(2,0),
                new Coord(3,0),
                new Coord(0,1)
        )));
        Assertions.assertEquals(Arrays.asList(
                "#### ",
                "#    ",
                "     ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));

        figure.rotate();
        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(0,0),
                new Coord(0,1),
                new Coord(0,2),
                new Coord(0,3),
                new Coord(1,3)
        )));
        Assertions.assertEquals(Arrays.asList(
                "#    ",
                "#    ",
                "#    ",
                "##   ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

    @Test
    public void flipTest1() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                "##   ",
                " #   ",
                " ##   ",
                "     ",
                "     "
        )));

        figure.flip();

        Assertions.assertTrue(figure.getCoords().containsAll(Arrays.asList(
                new Coord(1,0),
                new Coord(2,0),
                new Coord(1,1),
                new Coord(0,2),
                new Coord(1,2)
        )));
        Assertions.assertEquals(Arrays.asList(
                " ##  ",
                " #   ",
                "##   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

    @Test
    public void flipTest2() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                " #   ",
                "##   ",
                "##   ",
                "     ",
                "     "
        )));

        figure.flip();
        Assertions.assertEquals(Arrays.asList(
                "#    ",
                "##   ",
                "##   ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

    @Test
    public void flipTest3() {
        Figure figure = new Figure(Figure.toCoords(Arrays.asList(
                "#####",
                "     ",
                "     ",
                "     ",
                "     "
        )));

        figure.flip();
        Assertions.assertEquals(Arrays.asList(
                "#####",
                "     ",
                "     ",
                "     ",
                "     "
        ), Figure.toStrings(figure.getCoords(), 5, 5));
    }

}

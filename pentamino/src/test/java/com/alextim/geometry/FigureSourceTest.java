package com.alextim.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class FigureSourceTest {

    @Test
    public void test() throws FileNotFoundException {
        List<Figure> figures = new FigureSource("input1.txt").getFigures();
        Assertions.assertEquals(12, figures.size());
    }
}

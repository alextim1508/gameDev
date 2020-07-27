package com.alextim;

import com.alextim.algoX.AlgorithmX;
import com.alextim.frontend.Controller;
import com.alextim.geometry.Coord;
import com.alextim.geometry.Figure;
import com.alextim.geometry.FigureSource;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

@RequiredArgsConstructor @Slf4j
public class Pentamino {

    @Setter
    private Controller controller;

    public void start(int width, int height, File figureSourceFile, List<Coord> noBlackCell) throws FileNotFoundException, InterruptedException {

        List<Figure> figures = new FigureSource(figureSourceFile.getAbsolutePath()).getFigures();

        AlgorithmX algorithmX = new AlgorithmX(noBlackCell.size());

        Map<Integer, int[]> positions = preparation(width, height, figures, noBlackCell);
        positions.forEach(algorithmX::addPosition);

        algorithmX.linksDance();
        List<List<Integer>> answers = algorithmX.answers;
        log.info("Answers size {}", answers.size());

        showResult(width, height, answers, positions, noBlackCell);
    }

    private Map<Integer, int[]> preparation (int width, int height, List<Figure> figures, List<Coord> noBlackCell) {
        Map<Integer, int[]> positions = new HashMap<>();

        AtomicInteger n = new AtomicInteger();

        for (int f = 0; f < figures.size(); f++) {
            Set<List<Coord>> allPosition = figures.get(f).getAllPosition();
            log.debug("allPosition {}", allPosition);

            allPosition.forEach((List<Coord> coord) -> {

                for (int sx = 0; sx < width; sx++) {
                    for (int sy = 0; sy < height; sy++) {

                        boolean can = true;
                        for (int i = 0; i < coord.size(); i++) {
                            if (coord.get(i).x + sx >= width)
                                can = false;
                            if (coord.get(i).y + sy >= height)
                                can = false;

                            if (!noBlackCell.contains(new Coord(coord.get(i).x + sx, coord.get(i).y + sy)))
                                can = false;
                        }

                        if (!can)
                            continue;

                        int[] coveragesIndexes = new int[coord.size()];

                        for (int c = 0; c < coord.size(); c++) {
                            int index = 0;

                            for (int i = 0; i < height; i++) {
                                for (int j = 0; j < width; j++) {
                                    if (!noBlackCell.contains(new Coord(j, i)))
                                        continue;

                                    if ((j == (coord.get(c).x + sx)) && (i == (coord.get(c).y + sy)))
                                        coveragesIndexes[c] = index;

                                    index++;
                                }
                            }
                        }

                        Arrays.sort(coveragesIndexes);

                        positions.put(n.get(), coveragesIndexes);
                        n.incrementAndGet();
                    }
                }
            });
        }
        return positions;
    }


    private void showResult( int width, int height, List<List<Integer>> answers, Map<Integer, int[]> positions, List<Coord> noBlackCell) throws InterruptedException {
        for (int k = 0; k < answers.size(); k++) {

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if(noBlackCell.contains(new Coord(j ,i)))
                        controller.setColorCell(new Coord(j ,i), Color.WHITE);
                }
            }


            List<Integer> answer = answers.get(k);
            log.info("Answer {}", answer);

            for (int a = 0; a < answer.size(); a++) {
                for (int p : positions.get(answer.get(a))) {

                    int index =0;
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            if(!noBlackCell.contains(new Coord(j, i)))
                                continue;

                            if(index == p) {
                                Color color = controller.getColor(a);
                                int finalJ = j;
                                int finalI = i;
                                Platform.runLater(() -> {
                                    controller.setColorCell(new Coord(finalJ, finalI), color);
                                });
                            }
                            index++;
                        }
                    }
                }
            }

            synchronized(controller) {
                controller.wait();
            }
        }

        Platform.runLater(() -> {
            controller.setStatus("Выполнено");
        });
    }
}

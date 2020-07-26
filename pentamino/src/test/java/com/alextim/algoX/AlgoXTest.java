package com.alextim.algoX;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AlgoXTest {

    @Test
    public void test() {

        int[][] coverage = new int[][] {

                {0, 1, 2},
                {2, 3, 6},
                {4, 5, 8},
                {5, 6, 9},
                {6, 7, 10},

                {0, 2, 3},
                {2, 6, 7},
                {4, 8, 9},
                {5, 9, 10},
                {6, 10, 11},

                {1, 2, 3},
                {2, 5, 6},
                {3, 6, 7},
                {5, 8, 9},
                {6, 9, 10},
                {7, 10, 11},

                {0, 1, 3},
                {2, 3, 7},
                {4, 5, 9},
                {5, 6, 10},
                {6, 7, 11},
        };

        AlgorithmX algorithmX = new AlgorithmX(12);
        for (int i = 0; i < coverage.length; i++)
            algorithmX.addPosition(i, coverage[i]);

        algorithmX.linksDance();

        Assertions.assertEquals(0, algorithmX.answer.size());

        List<List<Integer>> answers = algorithmX.answers;
        System.out.println("answers = " + answers);

        Assertions.assertEquals(1, answers.size());
        Assertions.assertTrue(answers.get(0).containsAll(Arrays.asList(7, 11, 15, 16)));
    }

}

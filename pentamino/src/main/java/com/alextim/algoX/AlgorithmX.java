package com.alextim.algoX;

import java.util.*;
import java.util.stream.IntStream;

public class AlgorithmX {

    Header root;
    private Header[] headers;

    LinkedList<Integer> answer = new LinkedList<>();
    public List<List<Integer>> answers = new ArrayList<>();

    public AlgorithmX(int coverageSize) {
        root = new Header();
        headers = new Header[coverageSize];

        for (int i = 0; i < coverageSize; i++) {
            headers[i] = new Header(i);
            root.insertLeft(headers[i]);
        }
    }

    public void addPosition(int positionNumber, int[] coverageIndexes) {
        if(!isSorter(coverageIndexes))
           throw new RuntimeException("Must be sorted!");

        Node first = null;
        for (int i: coverageIndexes) {
            Node node = new Node(headers[i], positionNumber);
            headers[i].insertUp(node);
            headers[i].size++;

            if(first == null)
                first = node;
            else
                first.insertLeft(node);
        }
    }

    private boolean isSorter(int[] array) {
        return IntStream.range(0, array.length - 1).allMatch(i -> array[i] <= array[i + 1]);
    }

    public void linksDance() {
        Header header = getNextHeader();
        if(header == null) {
            answers.add(new ArrayList<>(answer));
            return;
        }

        remove(header);
        for (Node node = header.down; node != header; node = node.down) {

            answer.push(node.positionNumber);

            for (Node row = node.right; row != node; row = row.right)
                remove(row.header);

            linksDance();

            answer.pop();

            for (Node row = node.right; row != node; row = row.right)
                restore(row.header);
        }
        restore(header);
    }

    public void show() {
        System.out.println("current answer: " + answer);
        for (Header header = (Header) root.right; header != root ; header = (Header) header.right) {
            System.out.print(header.coverageIndex + ": ");
            for (Node node = header.down; node != header; node = node.down) {
                System.out.print(node.positionNumber + " ");
            }
            System.out.println();
        }
    }

    private Header getNextHeader() {
        Header minSizeHeader = null;
        int minSize = Integer.MAX_VALUE;
        for (Header header = (Header) root.right; header != root; header = (Header) header.right) {
            if(header.size < minSize) {
                minSize = header.size;
                minSizeHeader = header;
            }
        }
        return minSizeHeader;
    }

    private void remove(Header header) {
        header.right.left = header.left;
        header.left.right = header.right;

        for(Node node = header.down; node != header; node = node.down) {
            for (Node row = node.right; row != node; row = row.right) {
                row.down.up = row.up;
                row.up.down = row.down;
                row.header.size--;
            }
        }
    }

    private void restore(Header header) {
        header.right.left = header;
        header.left.right = header;

        for (Node node = header.down; node != header; node = node.down) {
            for(Node row = node.right; row != node; row = row.right) {
                row.down.up = row;
                row.up.down = row;
                row.header.size++;
            }
        }
    }
}

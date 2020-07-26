package com.alextim.algoX;

import lombok.ToString;

@ToString(exclude = {"up", "down", "left", "right"})
public class Node {
    public final Header header;
    public final int positionNumber;

    public Node left;
    public Node right;
    public Node down;
    public Node up;

    public Node(Header header, int positionNumber) {
        this.header = header;
        this.positionNumber = positionNumber;
        left = right = up = down = this;
    }

    void insertLeft(Node node) {
        left.right = node;
        node.left = left;

        left = node;
        node.right = this;
    }

    void insertUp(Node node) {
        up.down = node;
        node.up = up;

        up = node;
        node.down = this;
    }
}

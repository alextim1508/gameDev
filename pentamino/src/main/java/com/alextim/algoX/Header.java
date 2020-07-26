package com.alextim.algoX;

import lombok.ToString;

@ToString
public class Header extends Node {

    public final int coverageIndex;
    public int size;

    public Header(int coverageIndex) {
        super(null, -1);
        this.coverageIndex = coverageIndex;
    }

    public Header() {
        super(null, -1);
        this.coverageIndex = -1;
    }

}

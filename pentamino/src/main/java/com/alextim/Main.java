package com.alextim;

import com.alextim.frontend.Widget;

public class Main {

    public static void main(String[] args) {
        new Widget(args, new Pentamino()).showAndWait();
    }
}

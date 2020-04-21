package com.zfree.tank;

import java.util.Random;

public enum Dir {
    L, R, U, D;

    private static Random r = new Random();

    public static Dir randomDir() {
        return values()[r.nextInt(values().length)];
    }
}

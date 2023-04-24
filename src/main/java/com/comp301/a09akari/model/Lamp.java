package com.comp301.a09akari.model;

public class Lamp {
    private final int r;
    private final int c;

    public Lamp(int r, int c) {
        this.r = r;
        this.c = c;
    }
    public int getC(){
        return c;
    }
    public int getR(){
        return r;
    }
    public boolean equals(int r, int c) {
        return getC() == c && getR() == r;
    }
}

package com.comp301.a09akari.model;

public class Lamp {
    private int c;
    private int r;
    private Model model;

    public Lamp(int r, int c, Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        } else {
            this.model = model;
        }
        if (c < 0 || r < 0 || c >= model.getActivePuzzle().getWidth() || r >= model.getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        } else {
            this.r = r;
            this.c = c;
        }
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

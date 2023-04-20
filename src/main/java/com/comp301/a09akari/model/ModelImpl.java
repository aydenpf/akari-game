package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model{
    private PuzzleLibrary puzzles;
    private int index;
    private boolean[][] lamps;
    List<ModelObserver> observers;
    public ModelImpl(PuzzleLibrary library) {
        if (library == null) {
            throw new IllegalArgumentException();
        } else {
            puzzles = library;
            index = 0;
            lamps = new boolean[library.getPuzzle(index).getHeight()][library.getPuzzle(index).getWidth()];
            observers = new ArrayList<>();
        }
    }

    @Override
    public void addLamp(int r, int c) {
        if (indexOutOfBounds(r, c)) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r,c) == CellType.CORRIDOR) {
            lamps[r][c] = true;
            notifyObservers();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeLamp(int r, int c) {
        if (indexOutOfBounds(r, c)) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r,c) == CellType.CORRIDOR) {
            lamps[r][c] = false;
            notifyObservers();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isLit(int r, int c) {
        if (indexOutOfBounds(r,c)) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < getActivePuzzle().getWidth(); i++) {
            if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
                break;
            }
        }
    }

    @Override
    public boolean isLamp(int r, int c) {
        return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        return null;
    }

    @Override
    public int getActivePuzzleIndex() {
        return 0;
    }

    @Override
    public void setActivePuzzleIndex(int index) {

    }

    @Override
    public int getPuzzleLibrarySize() {
        return 0;
    }

    @Override
    public void resetPuzzle() {

    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        return false;
    }

    @Override
    public void addObserver(ModelObserver observer) {

    }

    @Override
    public void removeObserver(ModelObserver observer) {

    }
    private boolean indexOutOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= puzzles.getPuzzle(index).getHeight() || c >= puzzles.getPuzzle(index).getWidth();
    }

    private void notifyObservers() {

    }
}

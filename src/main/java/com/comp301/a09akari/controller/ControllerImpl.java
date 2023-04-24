package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
    private Model model;
    public ControllerImpl(Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        } else {
            this.model = model;
        }
    }

    @Override
    public void clickNextPuzzle() {
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }

    @Override
    public void clickPrevPuzzle() {
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }

    @Override
    public void clickRandPuzzle() {
        Random random = new Random();
        int index = random.nextInt(model.getPuzzleLibrarySize());
        model.setActivePuzzleIndex(index);
    }

    @Override
    public void clickResetPuzzle() {
        model.resetPuzzle();
    }

    @Override
    public void clickCell(int r, int c) {
        if (getActivePuzzle().getCellType(r,c) == CellType.CORRIDOR) {
            if (isLamp(r,c)) {
                model.removeLamp(r,c);
            } else {
                model.addLamp(r,c);
            }
        }
    }

    @Override
    public boolean isLit(int r, int c) {
        return model.isLit(r,c);
    }

    @Override
    public boolean isLamp(int r, int c) {
        return model.isLamp(r,c);
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        return model.isClueSatisfied(r,c);
    }

    @Override
    public boolean isSolved() {
        return model.isSolved();
    }

    @Override
    public Puzzle getActivePuzzle() {
        return model.getActivePuzzle();
    }
}

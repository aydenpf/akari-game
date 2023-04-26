package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    } else {
      this.model = model;
    }
  }

  @Override
  public void clickNextPuzzle() {
    try {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    } catch (IndexOutOfBoundsException e) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex());
    }
  }

  @Override
  public void clickPrevPuzzle() {
    try {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    } catch (IndexOutOfBoundsException e) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex());
    }
  }

  @Override
  public void clickRandPuzzle() {
    Random random = new Random();
    int index = random.nextInt(model.getPuzzleLibrarySize());
    if (index == model.getActivePuzzleIndex()) {
      clickRandPuzzle();
    } else {
      model.setActivePuzzleIndex(index);
    }
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (isLamp(r, c)) {
        model.removeLamp(r, c);
      } else {
        model.addLamp(r, c);
      }
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  public boolean isLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }

  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }

  public int getPuzzleLibrarySize() {
    return model.getPuzzleLibrarySize();
  }
}

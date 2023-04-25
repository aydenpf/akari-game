package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final PuzzleLibrary puzzles;
  private int index;
  private final List<Lamp> lamps;
  List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    } else {
      puzzles = library;
      index = 0;
      lamps = new ArrayList<>();
      observers = new ArrayList<>();
    }
  }

  @Override
  public void addLamp(int r, int c) {
    if (indexOutOfBounds(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      lamps.add(new Lamp(r, c));
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
    if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      for (Lamp lamp : lamps) {
        if (lamp.equals(r, c)) {
          lamps.remove(lamp);
          break;
        }
      }
      notifyObservers();
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (indexOutOfBounds(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else {
      if (isLamp(r, c)) {
        return true;
      } else {
        return (traverseDown(r + 1, c)
            || traverseUp(r - 1, c)
            || traverseLeft(r, c - 1)
            || traverseRight(r, c + 1));
      }
    }
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (indexOutOfBounds(r, c)) {
      throw new IndexOutOfBoundsException();
    } else {
      if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
        throw new IllegalArgumentException();
      } else {
        for (Lamp lamp : lamps) {
          if (lamp.equals(r, c)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (indexOutOfBounds(r, c)) {
      throw new IndexOutOfBoundsException();
    } else {
      if (isLamp(r, c)) {
        return traverseDown(r + 1, c)
            || traverseUp(r - 1, c)
            || traverseLeft(r, c - 1)
            || traverseRight(r, c + 1);
      } else {
        throw new IllegalArgumentException();
      }
    }
  }

  @Override
  public Puzzle getActivePuzzle() {
    return puzzles.getPuzzle(index);
  }

  @Override
  public int getActivePuzzleIndex() {
    return index;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= puzzles.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      this.index = index;
      notifyObservers();
    }
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzles.size();
  }

  @Override
  public void resetPuzzle() {
    lamps.clear();
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int r = 0; r < getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < getActivePuzzle().getWidth(); c++) {
        if (getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
          if (!isClueSatisfied(r, c)) {
            return false;
          }
        } else if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
          if (!isLit(r,c)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (indexOutOfBounds(r, c)) {
      throw new IndexOutOfBoundsException();
    } else {
      if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
        throw new IllegalArgumentException();
      } else {
        int lampCount =
            neighborCell(r + 1, c)
                + neighborCell(r - 1, c)
                + neighborCell(r, c + 1)
                + neighborCell(r, c - 1);
        return lampCount == getActivePuzzle().getClue(r, c);
      }
    }
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    } else {
      observers.add(observer);
    }
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    } else {
      observers.remove(observer);
    }
  }

  private boolean indexOutOfBounds(int r, int c) {
    return r < 0
        || c < 0
        || r >= getActivePuzzle().getHeight()
        || c >= getActivePuzzle().getWidth();
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  private boolean traverseUp(int r, int c) {
    if (r < 0) {
      return false;
    } else if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      return false;
    } else {
      if (isLamp(r, c)) {
        return true;
      } else {
        return traverseUp(r - 1, c);
      }
    }
  }

  private boolean traverseDown(int r, int c) {
    if (r >= getActivePuzzle().getHeight()) {
      return false;
    } else if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      return false;
    } else {
      if (isLamp(r, c)) {
        return true;
      } else {
        return traverseDown(r + 1, c);
      }
    }
  }

  private boolean traverseRight(int r, int c) {
    if (c >= getActivePuzzle().getWidth()) {
      return false;
    } else if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      return false;
    } else {
      if (isLamp(r, c)) {
        return true;
      } else {
        return traverseRight(r, c + 1);
      }
    }
  }

  private boolean traverseLeft(int r, int c) {
    if (c < 0) {
      return false;
    } else if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      return false;
    } else {
      if (isLamp(r, c)) {
        return true;
      } else {
        return traverseLeft(r, c - 1);
      }
    }
  }

  private int neighborCell(int r, int c) {
    if (indexOutOfBounds(r, c)) {
      return 0;
    } else {
      if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
        return 0;
      } else {
        if (isLamp(r, c)) {
          return 1;
        } else {
          return 0;
        }
      }
    }
  }
}

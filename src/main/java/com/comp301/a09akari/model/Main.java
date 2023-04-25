package com.comp301.a09akari.model;

public class Main {
  public static void main(String[] args) {
    int[][] grid = {{2, 6}, {6, 6}};
    Puzzle puzzle = new PuzzleImpl(grid);
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(puzzle);
    Model model = new ModelImpl(lib);

    model.addLamp(1, 0);
    model.addLamp(0, 1);
    System.out.println(model.isSolved());
  }
}

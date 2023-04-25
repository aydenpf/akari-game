package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    int[][] copy = new int[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
    }
    this.board = copy;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      throw new IndexOutOfBoundsException();
    } else {
      if (board[r][c] <= 4) {
        return CellType.CLUE;
      } else if (board[r][c] == 5) {
        return CellType.WALL;
      } else {
        return CellType.CORRIDOR;
      }
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      throw new IndexOutOfBoundsException();
    } else {
      if (getCellType(r, c) == CellType.CLUE) {
        return board[r][c];
      } else {
        throw new IllegalArgumentException();
      }
    }
  }
}

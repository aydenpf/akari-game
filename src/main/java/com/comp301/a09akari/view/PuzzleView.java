package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {
  private final AlternateMvcController controller;
  private int r;
  private int c;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
    r = 0;
    c = 0;
  }

  @Override
  public Parent render() {
    GridPane puzzle = new GridPane();
    puzzle.setHgap(3.0);
    puzzle.setVgap(3.0);
    puzzle.getStyleClass().add("puzzleView");

    // set each cell as a button
    Puzzle activePuzzle = controller.getActivePuzzle();
    while (r < activePuzzle.getHeight()) {
      while (c < activePuzzle.getWidth()) {
        if (activePuzzle.getCellType(r, c) == CellType.CORRIDOR) {
          Button lamp = new Button();
          lamp.setMinSize(65, 65);
          lamp.setPrefSize(65, 65);
          lamp.setMaxSize(65, 65);
          puzzle.add(lamp, c, r);
          lamp.setOnAction(
              (ActionEvent e) -> {
                controller.clickCell(GridPane.getRowIndex(lamp), GridPane.getColumnIndex(lamp));
              });

          if (controller.isLamp(GridPane.getRowIndex(lamp), GridPane.getColumnIndex(lamp))) {
            lamp.setText("Lamp");
            if (controller.isLampIllegal(
                GridPane.getRowIndex(lamp), GridPane.getColumnIndex(lamp))) {
              lamp.getStyleClass().add("lampIllegal");
            } else {
              lamp.getStyleClass().add("lampLegal");
            }
          } else if (controller.isLit(GridPane.getRowIndex(lamp), GridPane.getColumnIndex(lamp))) {
            lamp.setStyle("-fx-background-color: yellow;");
          }
        }
        if (activePuzzle.getCellType(r, c) == CellType.CLUE) {
          Button clue = new Button();
          puzzle.add(clue, c, r);
          clue.setMinSize(65, 65);
          clue.setPrefSize(65, 65);
          clue.setMaxSize(65, 65);
          clue.setText(String.valueOf(activePuzzle.getClue(r, c)));
          if (controller.isClueSatisfied(
              GridPane.getRowIndex(clue), GridPane.getColumnIndex(clue))) {
            clue.getStyleClass().add("clueSolved");
          } else {
            clue.getStyleClass().add("clueTextUnsolved");
          }
        }
        if (activePuzzle.getCellType(r, c) == CellType.WALL) {
          Button wall = new Button();
          wall.setMinSize(65, 65);
          wall.setPrefSize(65, 65);
          wall.setMaxSize(65, 65);
          wall.setStyle("-fx-background-color: black;");
          puzzle.add(wall, c, r);
        }
        c++;
      }
      c = 0;
      r++;
    }
    r = 0;

    return puzzle;
  }
}

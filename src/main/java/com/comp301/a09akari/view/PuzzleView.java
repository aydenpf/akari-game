package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class PuzzleView implements FXComponent{
    private AlternateMvcController controller;
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

        // set each corridor as a button
        Puzzle activePuzzle = controller.getActivePuzzle();
        while (r < activePuzzle.getHeight()) {
            while (c < activePuzzle.getWidth()) {
                if (activePuzzle.getCellType(r,c) == CellType.CORRIDOR) {
                    Button lamp = new Button();
                    lamp.setOnAction((ActionEvent e) -> {
                        controller.clickCell(r,c);
                    });
                    puzzle.add(lamp, c, r);
                }
                if (activePuzzle.getCellType(r,c) == CellType.CLUE) {
                    Label clue = new Label(String.valueOf(activePuzzle.getClue(r,c)));
                    puzzle.add(clue, c, r);
                }
                if (activePuzzle.getCellType(r,c) == CellType.WALL) {
                    Label wall = new Label();
                    puzzle.add(wall, c, r);
                }
            }
            c = 0;
        }
        r = 0;

        return puzzle;
    }
}

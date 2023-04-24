package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent{
    private AlternateMvcController controller;
    public ControlView(AlternateMvcController controller) {
        this.controller = controller;
    }
    @Override
    public Parent render() {
        HBox controls = new HBox();

        Button resetButton = new Button("Reset");
        resetButton.setOnAction((ActionEvent e) -> {
            controller.clickResetPuzzle();
        });
        controls.getChildren().add(resetButton);

        Button prevButton = new Button("Prev");
        prevButton.setOnAction((ActionEvent e) -> {
            controller.clickPrevPuzzle();
        });
        controls.getChildren().add(prevButton);

        Button randomButton = new Button("Random");
        randomButton.setOnAction((ActionEvent e) -> {
            controller.clickRandPuzzle();
        });
        controls.getChildren().add(randomButton);

        Button nextButton = new Button("Next");
        nextButton.setOnAction((ActionEvent e) -> {
            controller.clickNextPuzzle();
        });
        controls.getChildren().add(nextButton);

        return controls;
    }
}

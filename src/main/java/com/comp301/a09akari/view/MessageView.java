package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class MessageView implements FXComponent{
    private AlternateMvcController controller;
    public MessageView(AlternateMvcController controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        Label message = new Label();
        if (controller.isSolved()) {
            message.setText("Congrats, you completed the puzzle");
        } else {
            message.setText("Puzzle not completed");
        }
        return message;
    }
}

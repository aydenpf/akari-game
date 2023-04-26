package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MessageView implements FXComponent {
  private final AlternateMvcController controller;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.getStyleClass().add("message");

    Label message = new Label();
    layout.getChildren().add(message);
    if (controller.isSolved()) {
      message.setText("Congrats, you completed the puzzle");
      message.getStyleClass().add("messageCorrect");
    } else {
      message.setText("Puzzle not completed");
      message.getStyleClass().add("messageIncorrect");
    }
    Label puzzleNum = new Label();
    layout.getChildren().add(puzzleNum);
    puzzleNum.setText(
        "Puzzle "
            + (controller.getActivePuzzleIndex() + 1)
            + "/"
            + controller.getPuzzleLibrarySize());

    return layout;
  }
}

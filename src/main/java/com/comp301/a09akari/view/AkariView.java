package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AkariView implements FXComponent, ModelObserver {

  private final AlternateMvcController controller;
  private final Stage stage;

  public AkariView(AlternateMvcController controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.getStyleClass().add("akari");

    ControlView controls = new ControlView(controller);
    PuzzleView puzzle = new PuzzleView(controller);
    MessageView message = new MessageView(controller);

    layout.getChildren().add(message.render());
    layout.getChildren().add(controls.render());
    layout.getChildren().add(puzzle.render());

    return layout;
  }

  @Override
  public void update(Model model) {
    Scene scene = new Scene(render(), 750, 750);
    scene.getStylesheets().add("main.css");
    stage.centerOnScreen();
    stage.setScene(scene);
  }
}

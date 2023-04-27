package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    BorderPane layout = new BorderPane();
    layout.getStyleClass().add("akari");
    layout.setBackground(
        new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(1), null)));

    ControlView controls = new ControlView(controller);
    PuzzleView puzzle = new PuzzleView(controller);
    MessageView message = new MessageView(controller);

    layout.setTop(controls.render());
    layout.setCenter(puzzle.render());
    layout.setBottom(message.render());

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

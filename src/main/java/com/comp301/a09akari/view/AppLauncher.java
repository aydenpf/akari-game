package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI

    // Puzzles to create puzzle library
    Puzzle puzzle1 = new PuzzleImpl(com.comp301.a09akari.SamplePuzzles.PUZZLE_01);
    Puzzle puzzle2 = new PuzzleImpl(com.comp301.a09akari.SamplePuzzles.PUZZLE_02);
    Puzzle puzzle3 = new PuzzleImpl(com.comp301.a09akari.SamplePuzzles.PUZZLE_03);
    Puzzle puzzle4 = new PuzzleImpl(com.comp301.a09akari.SamplePuzzles.PUZZLE_04);
    Puzzle puzzle5 = new PuzzleImpl(com.comp301.a09akari.SamplePuzzles.PUZZLE_05);
    PuzzleLibrary puzzles = new PuzzleLibraryImpl();
    puzzles.addPuzzle((puzzle1));
    puzzles.addPuzzle((puzzle2));
    puzzles.addPuzzle((puzzle3));
    puzzles.addPuzzle((puzzle4));
    puzzles.addPuzzle((puzzle5));

    // create Model
    Model model = new ModelImpl(puzzles);

    // create Controller
    AlternateMvcController controller = new ControllerImpl(model);

    // create view
    AkariView game = new AkariView(controller, stage);
    model.addObserver(game);

    stage.setTitle("Ayden Franklin Akari");
    game.update(model);

    stage.show();
  }
}

package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Model;
import view.View;


public class ControllerImpl implements Controller {

  private final Model gameModel;
  private final View gameView;
  private final Readable input;

  public ControllerImpl(Model m, View v, Readable r) throws IllegalArgumentException {
    if (m == null || v == null || r == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.gameModel = m;
    this.gameView = v;
    this.input = r;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(input);
    try {
      this.gameView.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    int[] moveIntegers = new int[2];
    int counter = 0;

    while (!this.gameModel.isGameOver()) {
      if (scanner.hasNext()) {
        String input = scanner.next();

        // check if it is q or not
        if (input.equals("q") || input.equals("Q")) {
          try {
            gameView.renderMessage("\nGame quit!\n");
            gameView.renderMessage("State of game when quit:\n");
            gameView.renderBoard();
            return;
          } catch (IOException e) {
            throw new IllegalStateException();
          }

          // if it's not "q" or "Q"
        } else {

          try {
            int inputInt = Integer.valueOf(input);
            moveIntegers[counter] = inputInt;
            if (counter == 1) {
              try {
                this.gameModel.move(moveIntegers[0] - 1, moveIntegers[1] - 1);
                moveIntegers = new int[2];
                counter = 0;

                try {
                  this.gameView.renderBoard();
                } catch (IOException e) {
                  throw new IllegalStateException();
                }
              }

              // if move is invalid, reset array and ask for new values
              catch (IllegalArgumentException e) {
                moveIntegers = new int[2];
                counter = 0;
                this.invalidMoveMessage();
              }
            } else {
              counter++;
            }
          } catch (NumberFormatException e) {
            this.reenterMessage();
          }
        }
      }
//      if there is no next input, throw an exception
      else {
        throw new IllegalStateException("Readable ran out of inputs");
      }

    }

//    exits while loop - game is over
    try {
      gameView.renderMessage("\nGame over!\n");
      gameView.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  //  todo: add to interface
  private void reenterMessage() {
    try {
      this.gameView.renderMessage("Please reenter value: \n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  private void invalidMoveMessage() {
    try {
      this.gameView.renderMessage("Invalid move. Play again. \n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

}

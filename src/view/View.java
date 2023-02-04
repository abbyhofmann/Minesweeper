package view;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by a view for the Minesweeper game.
 */
public interface View {

  /**
   * Represents the state of the board as a string. Unexpanded cells and mines are represented as
   * "_ ", whereas expanded cells are represented by their number of adjacent mines. So, when a
   * player "clicks" on a cell to expand it, the view of the cell will be updated from "_ " to the
   * number of adjacent cells so the player can progress in the game and begin to figure out where
   * the mines are located.
   *
   * @return The state of the board represented as a string.
   */
  String toString();

  /**
   * Renders the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above.
   *
   * @throws IOException If transmission of the board to the provided data destination fails.
   */
  void renderBoard() throws IOException;

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message The message to be transmitted.
   * @throws IOException If transmission of the board to the provided data destination fails.
   */
  void renderMessage(String message) throws IOException;
}

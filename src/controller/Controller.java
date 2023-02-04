package controller;

/**
 * Represents the controller for a game of Minesweeper. The controller will work with a
 * Model, and this model will provide the controller with the necessary and
 * relevant information to play the game. The controller then passes this information on to
 * the view, which can do things like render the board and render messages.
 */
public interface Controller {

  /**
   * Plays a new game of Minesweeper. A user has the ability to provide input, which the
   * method reads/interprets and acts accordingly. The input can represent a cell to "click"/expand,
   * and the user can hit "q" ot "Q" to quit the game at any time.
   *
   * @throws IllegalStateException Exception thrown if controller is unable to read or transmit.
   */
  void playGame() throws IllegalStateException;
}

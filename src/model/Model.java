package model;

import java.util.ArrayList;

/**
 * This interface represents the operations offered by the Minesweeper model. One object of the
 * model represents one game of Minesweeper.
 */
public interface Model {

  /**
   * Initializes the board that represents the board game space in Minesweeper. A board is typically
   * comprised of cells.
   */
  void initializeBoard();

  /**
   * Sets cells of the board to be mines in the game.
   */
  void setMines();

  /**
   * For a specific cell of the board, calculates the number of adjacent mines based on the set
   * mines and the cell's neighboring cells.
   *
   * @param row The row position of the cell whose adjacent mines are being calculated.
   * @param col The column position of the cell whose adjacent mines are being calculated.
   * @return Returns an integer representing the number of adjacent mines to the provided cell.
   */
  int calculateAdjMines(int row, int col);

  /**
   * For each cell in the board, updates the number of adjacent mines to reflect the mine
   * assignments, using the calculateAdjMines method to do so. In the game, this allows the player
   * to be able to deduce where the mines are located.
   */
  void setNeighbors();

  /**
   * Gets the number of rows of this board model.
   *
   * @return Integer representing number of rows.
   */
  int getRows();

  /**
   * Gets the number of columns of this board model.
   *
   * @return Integer representing number of rows.
   */
  int getCols();

  /**
   * Gets the 2d array of Cells of this board model.
   *
   * @return 2d array of cells representing the board.
   */
  Cell[][] getBoard();

  /**
   * Determines if the game is over.
   *
   * @return Boolean value; if ture, game is over.
   */
  boolean isGameOver();

  /**
   * Method called to represent a click on the board, where row is the row value and col is the
   * column value. If the corresponding cell is a mine, the player loses. If the corresponding cell
   * is not a mine, the corresponding cell state will become expanded, the specifications of which
   * are determined upon implementation.
   *
   * @param row The row of the location of the board "clicked".
   * @param col The column of the location of the board "clicked".
   */
  void move(int row, int col);

  /**
   * Progresses the game by expanding neighboring cells of a cell that was clicked.
   *
   * @param rowClicked The row of the cell that was clicked.
   * @param colCLicked The column of the cell that was clicked.
   */
  void expandHelper(int rowClicked, int colCLicked);

  /**
   * Determines the neighboring unexplored cells of the cell at the given row, column position.
   *
   * @param row Integer representing the row position of the cell whose neighbors are being stored.
   * @param col Integer representing the column position of the cell whose neighbors are being
   *            stored.
   * @return Returns an ArrayList of ArrayList of Integers. The inner ArrayList of Integers stores
   * each individual cell as an ArrayList of the row and column of the neighboring cell.
   */
  ArrayList<ArrayList<Integer>> cellNeighbors(int row, int col);
}

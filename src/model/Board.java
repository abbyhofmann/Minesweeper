package model;

import java.util.ArrayList;

/**
 * Represents the board in a Minesweeper game. The rows and columns fields determine the dimensions
 * of the game board. The minesOnBoard field represents the number of mines on the board in the game.
 * The board itself is represented by a 2-d array of Cells, where each cell has an associated state
 * and number of adjacent mines. The gameOver field reflects whether the board reflects a
 * finished game (either lost when the player hits a mine or won when all the non-mine fields have
 * been expanded).
 */
public class Board implements Model {

  private int rows;
  private int columns;
  private int minesOnBoard;
  private Cell[][] board;
  private boolean gameOver;

  /**
   * Initializes a new board given the number of rows and columns for the dimensions, assigning
   * the mine locations randomly.
   *
   * @param r The number of rows in the board.
   * @param c The number of columns in the board.
   */
  public Board(int r, int c, int numMines) throws IllegalArgumentException {
    if (r < 4 || c < 4) {
      throw new IllegalArgumentException("Board too small");
    }
    if (numMines < 1) {
      throw new IllegalArgumentException("Invalid number of mines");
    }
    this.rows = r;
    this.columns = c;
    this.minesOnBoard = numMines;
    this.board = new Cell[r][c];
    this.gameOver = false;

    this.initializeBoard();
    this.setMines();
    this.setNeighbors();
  }

  /**
   * Initializes a 7x7 board with mines located at (1,2), (4,4), and (4,2) with 0-based indexing.
   */
  public Board() {
    this.rows = 7;
    this.columns = 7;
    this.minesOnBoard = 3;
    this.board = new Cell[7][7];
    this.gameOver = false;

    this.initializeBoard();
    // set mine locations manually
    this.board[1][2].setMine();
    this.board[4][4].setMine();
    this.board[4][2].setMine();
    this.setNeighbors();
  }

  /**
   * For each cell in the board, updates the number of adjacent mines to reflect the mine
   * assignments. In the game, this allows the player to be able to deduce where the mines are
   * located.
   */
  public void setNeighbors() {
    for (int r = 0; r < this.rows; r++) {
      for (int c = 0; c < this.columns; c++) {
        Cell cell = this.board[r][c];
        int adjacentMines = this.calculateAdjMines(r, c);
        cell.setAdjacentMines(adjacentMines);
      }
    }
  }

  /**
   * Returns the number of rows of this board.
   *
   * @return Returns an integer reflecting the number of rows in the board.
   */
  @Override
  public int getRows() {
    return this.rows;
  }

  /**
   * Returns the number of columns of this board.
   *
   * @return Returns an integer reflecting the number of columns in the board.
   */
  @Override
  public int getCols() {
    return this.columns;
  }

  /**
   * Calculates the number of mines adjacent to a cell, given the cell's row and column values. For
   * each cell, there is a maximum of 8 adjacent cells.
   *
   * @param row Row value for the cell.
   * @param col Column value for the cell.
   * @return Returns an integer representing how many of the adjacent cells are mines.
   */
  public int calculateAdjMines(int row, int col) {
    int mineCount = this.board[row][col].getAdjMines();
    for (int r = row - 1; r <= row + 1; r++) {
      for (int c = col - 1; c <= col + 1; c++) {
        if (this.isValidCell(r, c) && this.board[r][c].isMine()) {
          mineCount++;
        }
      }
    }
    return mineCount;
  }

  /*
  Determines if a cell is on the board given its row and column values.
   */

  /**
   * Determines if a cell is on the board given its row and column values. The given values cannot
   * be negative and must fall within the row/column values of this board.
   *
   * @param r Integer row value of the cell being checked if valid.
   * @param c Integer column value of the cell being checked if valid.
   * @return Returns true if the cell at the r, c position falls on the board.
   */
  public boolean isValidCell(int r, int c) {
    return r >= 0 && r < this.rows && c >= 0 && c < this.columns;
  }

  /**
   * Sets the states of randomly selected cells to be mines. The number of cells set is determined
   * by the number of mines on the board.
   */
  public void setMines() {
    int minesSet = 0;
    int r, c;
    while (minesSet != this.minesOnBoard) {
      c = (int) Math.floor(Math.random() * this.columns);
      r = (int) Math.floor(Math.random() * this.rows);

      if (!this.board[r][c].isMine()) {
        this.board[r][c].setMine();
        minesSet++;
      }
    }
  }

  /**
   * Initializes the 2-d list of cells that comprise the board. Initially, all cells are set as
   * unexpanded with 0 adjacent mines.
   */
  public void initializeBoard() {
    for (int r = 0; r < this.rows; r++) {
      for (int c = 0; c < this.columns; c++) {
        this.board[r][c] = new Cell();
      }
    }
  }

  /**
   * Gets the 2-d array of cells of the board.
   *
   * @return Returns a 2-d array of cells that represent this board.
   */
  public Cell[][] getBoard() {
    return this.board;
  }

  /**
   * Determines if a game is over. A game is over when 1) every non-mine cell is expanded or 2) the
   * gameOver field is set to false because the player has selected a mine cell.
   *
   * @return True if the game is over (won/lost).
   */
  @Override
  public boolean isGameOver() {
    boolean allExpanded = true;
    for (int r = 0; r < this.rows; r++) {
      for (int c = 0; c < this.columns; c++) {
        Cell cell = this.board[r][c];
        if (cell.isUnexpanded()) {
          allExpanded = false;
        }
      }
    }
    return this.gameOver || allExpanded;
  }

  /**
   * Method called when a player enters a row and column to represent a click on the board. If the
   * corresponding cell is a mine, the player loses. If the corresponding cell is not a mine, the
   * corresponding cell state will become expanded. If the cell has no adjacent mines, all its
   * neighbors will become expanded and those neighbors will recursively be expanded only if they
   * have no adjacent mines.
   *
   * @param row Row of the cell "clicked".
   * @param col Column of the cell "clicked".
   */
  @Override
  public void move(int row, int col) throws IllegalArgumentException {
    if (!this.isValidCell(row, col)) {
      throw new IllegalArgumentException("Invalid cell");
    }
    Cell cellClicked = this.board[row][col];
    if (cellClicked.isMine()) {
      this.gameOver = true;
    }
    this.expandHelper(row, col);
  }

  /**
   * Recursively expands cells when one is clicked to progress the game. If the cell at the
   * rowClicked, colClicked position is unexpanded, the that cell state become expanded. If the cell
   * also has no adjacent mines, the surrounding adjacent cells need to be looked at and expanded
   * in order for the game to progress. So, an ArrayList of ArrayList stores the rows/columns of the
   * cell's neighbors that are unexpanded, and the method is called on each of those neighbors.
   *
   * @param rowClicked The row of the cell that was clicked.
   * @param colClicked The column of the cell that was clicked.
   */
  @Override
  public void expandHelper(int rowClicked, int colClicked) {
    Cell cellClicked = this.board[rowClicked][colClicked];
    if (cellClicked.isUnexpanded()) {
      cellClicked.setExpanded();
      if (cellClicked.getAdjMines() == 0) {
        ArrayList<ArrayList<Integer>> neighbors = this.cellNeighbors(rowClicked, colClicked);
        for (ArrayList<Integer> neighbor : neighbors) {
          this.expandHelper(neighbor.get(0), neighbor.get(1));
        }
      }
    }
  }

  /**
   * Determines the neighboring unexplored cells of the cell at the given row, column position.
   *
   * @param row Integer representing the row position of the cell whose neighbors are being stored.
   * @param col Integer representing the column position of the cell whose neighbors are being
   *            stored.
   * @return Returns an ArrayList of ArrayList of Integers. The inner ArrayList of Integers stores
   * each individual cell as an ArrayList of the row and column of the neighboring cell.
   */
  @Override
  public ArrayList<ArrayList<Integer>> cellNeighbors(int row, int col) {
    ArrayList<ArrayList<Integer>> neighborList = new ArrayList<>();
    for (int r = row - 1; r <= row + 1; r++) {
      for (int c = col - 1; c <= col + 1; c++) {
        if (this.isValidCell(r, c) && this.board[r][c].isUnexpanded()) {
          ArrayList<Integer> rowColList = new ArrayList<>();
          rowColList.add(r);
          rowColList.add(c);
          neighborList.add(rowColList);
        }
      }
    }
    return neighborList;
  }
}

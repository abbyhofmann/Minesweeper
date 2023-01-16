package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Represents the board in a minesweeper game.
public class Board implements Model {

  private int rows;
  private int columns;
  private int minesOnBoard;
  private Cell[][] board;
  private boolean gameOver;

  /**
   * Initializes a new board given the number of rows and columns for the dimensions, assigning
   * the mine locations randomly.
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
   * Initializes a 7x7 board with mines located at (1,2) and (4,4) with 0-based indexing. 
   */
  public Board() {
    this.rows = 7;
    this.columns = 7;
    this.minesOnBoard = 2;
    this.board = new Cell[7][7];
    this.gameOver = false;

    this.initializeBoard();
    // set mine locations manually
    this.board[1][2].setMine();
    this.board[4][4].setMine();
    this.board[4][2].setMine();
    this.setNeighbors();
  }

  /*
  For each cell in the board, updates the number of adjacent mines to reflect the mine
  assignments.
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

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getCols() {
    return this.columns;
  }

  /**
   * Calculates the number of mines adjacent to a cell, given the cell's row and column values. For
   * each cell, there is a maximum of 8 adjacent cells.
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
  public boolean isValidCell(int r, int c) {
    return r >= 0 && r < this.rows && c >= 0 && c < this.columns;
  }

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

  /*
  Creates the 2d list of cells that comprise the board/
   */
  public void initializeBoard() {
    for (int r = 0; r < this.rows; r++) {
      for (int c = 0; c < this.columns; c++) {
        this.board[r][c] = new Cell();
      }
    }
  }

  public Cell[][] getBoard() {
    return this.board;
  }

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
    return this.gameOver || allExpanded;   // todo: create a for loop that check every non-mine cell to see if it is expanded --> local variable joined with this.gameOver with an OR clause (||)

    /*
    game is over when...
    1. player hits a mine --> Loss
    2. player successfully clicks on every board that isn't a mine --> Win
     */


  }

  /**
   * Method called when a player enters a row and column to represent a click on the board. If the
   * corresponding cell is a mine, the player loses. If the corresponding cell is not a mine, the
   * corresponding cell state will become expanded. If the cell has no adjacent mines, all its
   * neighbors will become expanded and those neighbors will recursively be expanded only if they
   * have no adjacent mines.
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
        /*
        for each neighbor of cellClicked {
          if unexpanded {
            set expanded
            recursive call to helper

        helper method in cell class:
        cellClicked.expandHelper();
        public void expandHelper() {
          if this.isUnexpanded() {
            this.setExpanded();
            if (this.getAdjMines() == 0) {
              Cell[] neighbors = this.cellNeighbors();
              for (Cell neighbor : neighbors) {
                neighbor.expandHelper();
              }
            }
          }
         */
  }

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

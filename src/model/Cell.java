package model;

// Represents a single cell in the game.
public class Cell {
  private CellState state;

//  private boolean mine;
  private int adjacentMines;

  /*
  Initializes a cell to not be a mine, where its label is an empty string
  and it has no adjacent mines.
   */
  public Cell() {
    this.state = CellState.Unexpanded;
    this.adjacentMines = 0;
  }

  /*
  Returns the mine field of the cell, indicating whether or not the cell represents a mine.
   */
  public boolean isMine() {
    return this.state.equals(CellState.Mine);
  }

  public boolean isExpanded() {
    return this.state.equals(CellState.Expanded);
  }

  public boolean isUnexpanded() {
    return this.state.equals(CellState.Unexpanded);
  }

  /*
  Sets the mine field to be Mine enum, meaning the cell now represents a mine.
   */
  public void setMine() {
    this.state = CellState.Mine;
  }

  /**
   * Sets the adjacentMines field to equal the given integer.
   * @param adjacentMines Integer value representing the number of adjacent mines to this cell.
   */
  public void setAdjacentMines(int adjacentMines) {
    this.adjacentMines = adjacentMines;
  }

  public int getAdjMines() {
    return this.adjacentMines;
  }

  public void setExpanded() {
    this.state = CellState.Expanded;
  }
}

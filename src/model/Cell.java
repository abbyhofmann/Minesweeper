package model;

/**
 * Represents a single cell in the game. CellState represents whether a cell is a mine, has been
 * expanded (ie clicked on), or is unexpanded. Each cell also stores information about the number
 * of mines it is adjacent to.
 */
public class Cell {
  private CellState state;
  private int adjacentMines;

  /**
   * Initializes a cell to be unexpanded with 0 adjacent mines.
   */
  public Cell() {
    this.state = CellState.Unexpanded;
    this.adjacentMines = 0;
  }

  /**
   * Determines if a cell represents a mine.
   *
   * @return Returns true if the cell's state is mine.
   */
  public boolean isMine() {
    return this.state.equals(CellState.Mine);
  }

  /**
   * Determines if a cell is expanded.
   *
   * @return Returns true if the cell's state is expanded.
   */
  public boolean isExpanded() {
    return this.state.equals(CellState.Expanded);
  }

  /**
   * Determines if a cell is unexpanded.
   *
   * @return Returns true if the cell's state is unexpanded.
   */
  public boolean isUnexpanded() {
    return this.state.equals(CellState.Unexpanded);
  }

  /**
   * Sets the state of the cell to be mine, so the cell then represents a mine.
   */
  public void setMine() {
    this.state = CellState.Mine;
  }

  /**
   * Sets the adjacentMines field to equal the given integer.
   *
   * @param adjacentMines Integer value representing the number of adjacent mines to this cell.
   */
  public void setAdjacentMines(int adjacentMines) {
    this.adjacentMines = adjacentMines;
  }

  /**
   * Returns the number of adjacent mines of the cell.
   *
   * @return Integer value representing the number of mines adjacent to this cell.
   */
  public int getAdjMines() {
    return this.adjacentMines;
  }

  /**
   * Sets the cell's state to be expanded.
   */
  public void setExpanded() {
    this.state = CellState.Expanded;
  }
}

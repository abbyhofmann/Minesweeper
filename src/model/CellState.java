package model;

/**
 * Represents the state of a cell. Mine means the cell represents a mine in the game. Expanded means
 * a cell has been clicked on or is adjacent to an expanded cell that has zero adjacent mines.
 * Unexpanded means a cell has not been clicked on.
 */
public enum CellState {
  Mine, Expanded, Unexpanded
}

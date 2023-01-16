package model;

import java.util.ArrayList;

public interface Model {
  void initializeBoard();
  void setMines();
  int calculateAdjMines(int row, int col);
  void setNeighbors();
  int getRows();
  int getCols();
  Cell[][] getBoard();
  boolean isGameOver();

  void move(int row, int col);

  void expandHelper(int rowClicked, int colCLicked);

  ArrayList<ArrayList<Integer>> cellNeighbors(int row, int col);
}

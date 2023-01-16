package view;

import java.io.IOException;

import model.Model;

public class TextView implements View {

  private final Model model;
  private final Appendable destination;

  public TextView(Model m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Provided model cannot be null");
    }
    this.model = m;
    this.destination = System.out;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    String stringResult = "";
    for (int r = 0; r < this.model.getRows(); r++) {
      for (int c = 0; c < this.model.getCols(); c++) {
//        if (this.model.getBoard()[r][c].isMine()) {
//          result.append("M");
//        } else
        if (this.model.getBoard()[r][c].isUnexpanded() || this.model.getBoard()[r][c].isMine()) {
          result.append("_ ");
        } else if (this.model.getBoard()[r][c].isExpanded()) {
          result.append(this.model.getBoard()[r][c].getAdjMines() + " ");
        }
      }
      stringResult = result.substring(0, result.length());
      if (r != model.getRows() - 1) {
        stringResult += "\n";
      }
      result = new StringBuilder(stringResult);
    }
    return stringResult;

  }

  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}

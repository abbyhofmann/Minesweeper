package view;

import java.io.IOException;

import model.Model;

/**
 * Represents a view of the Board state via implementation of the View interface. The class requires
 * a model to be represented, as well as an Appendable to represent the board as text.
 */
public class TextView implements View {

  private final Model model;
  private final Appendable destination;

  /**
   * Constructs a TextView given a model to represent. The appendable is set to System.out to print
   * the view of the board.
   *
   * @param m The model to view.
   * @throws IllegalArgumentException Exception thrown if provided model is null.
   */
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

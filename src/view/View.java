package view;

import java.io.IOException;

public interface View {
  String toString();
  void renderBoard() throws IOException;
  void renderMessage(String message) throws IOException;
}

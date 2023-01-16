import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;
import model.Board;
import model.Model;
import view.TextView;
import view.View;

public class MineSweeperMain {

//  default
//  custom -columns 8 -rows 8 -mines 4

  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    Model model;
    View view;
    Controller controller;

    int rows = 0;
    int cols = 0;
    int numMines = 0;

    if (args.length > 0 && args != null) {
      for (int i = 1; i < args.length; i = i + 2) {
        switch (args[i]) {
          case "-rows":
            try {
              rows = Integer.parseInt(args[i + 1]);
            } catch (NumberFormatException e) {
              System.out.println("Integer expected for rows");
              System.exit(0);
            }
            break;

          case "-columns":
            try {
              cols = Integer.parseInt(args[i + 1]);
            } catch (NumberFormatException e) {
              System.out.println("Integer expected for columns");
              System.exit(0);
            }
            break;

          case "-mines":
            try {
              numMines = Integer.parseInt(args[i + 1]);
            } catch (NumberFormatException e) {
              System.out.println("Integer expected for number of mines");
              System.exit(0);
            }
            break;

          default:
            System.out.println("Unexpected string entered");
            System.exit(0);
        }
      }
      switch (args[0]) {
        case "default":
          model = new Board();
          view = new TextView(model);
          controller = new ControllerImpl(model, view, rd);
          controller.playGame();
          break;

        case "custom":
          model = new Board(rows, cols, numMines);
          view = new TextView(model);
          controller = new ControllerImpl(model, view, rd);
          controller.playGame();
          break;

        default:
          System.out.println("No valid model entered.");
          System.exit(0);
          break;
      }

    } else {
      System.out.println("No inputs entered");
      System.exit(0);
    }

  }

}

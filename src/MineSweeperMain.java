import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;
import model.Board;
import model.Model;
import view.TextView;
import view.View;

/**
 * This class contains the main method, which runs the game based on an array of string user
 * inputs from the command line. Based on the arguments passed in, a model of a Minesweeper
 * game will be instantiated, as well as a corresponding view and controller. It is through this
 * MVC pattern that the game operates.
 */
public class MineSweeperMain {

  /**
   * Main method for running playGame(). The user must specify a model, either "default" or
   * "custom," and they can additionally specify the number of rows using the "-rows" command
   * followed by an integer, the number of columns using the "-columns" command followed by an
   * integer, and the number of mines on the board using the "-mines" command followed by an integer.
   *
   * @param args The array of string representing user input from the command line.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Model model;
    View view;
    Controller controller;

    int rows = 0;
    int cols = 0;
    int numMines = 0;

    // if there is input from the user
    if (args.length > 0 && args != null) {
      for (int i = 1; i < args.length; i = i + 2) {
        // updates the rows, cols, and numMines local variables to reflect user input, if applicable
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

      /*
      Instantiates model, view, and controller based on whether the user specifies the default
      game or a custom game.
       */
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

    }
    // if no inputs are provided
    else {
      System.out.println("No inputs entered");
      System.exit(0);
    }
  }
}

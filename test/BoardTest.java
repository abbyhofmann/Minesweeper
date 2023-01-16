import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

  Board board1;

  @BeforeEach
  public void setup() {
    this.board1 = new Board();
  }

  @Test
  public void testDefaultConstructor() {
    assertEquals(false, this.board1.getBoard()[0][0].isMine());
    assertEquals(true, this.board1.getBoard()[1][2].isMine());
    assertEquals(true, this.board1.getBoard()[4][4].isMine());
    assertEquals(true, this.board1.getBoard()[4][2].isMine());
    assertEquals(false, this.board1.getBoard()[0][6].isMine());
    assertEquals(false, this.board1.getBoard()[6][0].isMine());
    assertEquals(false, this.board1.getBoard()[6][6].isMine());
    assertEquals(false, this.board1.getBoard()[4][5].isMine());
    assertEquals(false, this.board1.getBoard()[3][3].isMine());

  }

  @Test
  public void testSetNeighbors() {
    assertEquals(1, this.board1.getBoard()[0][1].getAdjMines());
    assertEquals(1, this.board1.getBoard()[0][2].getAdjMines());
    assertEquals(1, this.board1.getBoard()[0][3].getAdjMines());
    assertEquals(1, this.board1.getBoard()[1][1].getAdjMines());
    assertEquals(1, this.board1.getBoard()[1][3].getAdjMines());
    assertEquals(1, this.board1.getBoard()[2][1].getAdjMines());
    assertEquals(1, this.board1.getBoard()[2][2].getAdjMines());
    assertEquals(1, this.board1.getBoard()[2][3].getAdjMines());
    assertEquals(2, this.board1.getBoard()[3][3].getAdjMines());
    assertEquals(1, this.board1.getBoard()[3][4].getAdjMines());
    assertEquals(1, this.board1.getBoard()[3][5].getAdjMines());
    assertEquals(2, this.board1.getBoard()[4][3].getAdjMines());
    assertEquals(1, this.board1.getBoard()[4][5].getAdjMines());
    assertEquals(2, this.board1.getBoard()[5][3].getAdjMines());
    assertEquals(1, this.board1.getBoard()[5][4].getAdjMines());
    assertEquals(1, this.board1.getBoard()[5][5].getAdjMines());
    assertEquals(1, this.board1.getBoard()[3][1].getAdjMines());
    assertEquals(1, this.board1.getBoard()[3][2].getAdjMines());
    assertEquals(1, this.board1.getBoard()[4][1].getAdjMines());
    assertEquals(1, this.board1.getBoard()[5][1].getAdjMines());
    assertEquals(1, this.board1.getBoard()[5][2].getAdjMines());

    assertEquals(0, this.board1.getBoard()[0][0].getAdjMines());
    assertEquals(0, this.board1.getBoard()[6][6].getAdjMines());
  }

  @Test
  public void testIsValidCell() {
    assertEquals(false, this.board1.isValidCell(7,7));
    assertEquals(false, this.board1.isValidCell(10,6));
    assertEquals(false, this.board1.isValidCell(0,12));
    assertEquals(false, this.board1.isValidCell(-1,5));
    assertEquals(false, this.board1.isValidCell(2,-20));
    assertEquals(false, this.board1.isValidCell(-1,-1));
    assertEquals(true, this.board1.isValidCell(0,0));
    assertEquals(true, this.board1.isValidCell(0,6));
    assertEquals(true, this.board1.isValidCell(6,0));
    assertEquals(true, this.board1.isValidCell(6,6));
  }

}
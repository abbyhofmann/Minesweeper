import org.junit.jupiter.api.BeforeEach;

import model.Cell;

import static org.junit.Assert.assertEquals;

class CellTest {

  Cell cell1;

  @BeforeEach
  public void setup() {
    this.cell1 = new Cell();
  }

  @org.junit.jupiter.api.Test
  public void testIsMine() {
    assertEquals(false, cell1.isMine());
  }

  @org.junit.jupiter.api.Test
  public void testSetMine() {
    assertEquals(false, cell1.isMine());
    this.cell1.setMine();
    assertEquals(true, cell1.isMine());
  }

  @org.junit.jupiter.api.Test
  public void testAdjacentMines() {
    assertEquals(0, this.cell1.getAdjMines());
    this.cell1.setAdjacentMines(3);
    assertEquals(3, this.cell1.getAdjMines());
  }

}
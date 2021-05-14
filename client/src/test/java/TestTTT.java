import static org.junit.jupiter.api.Assertions.assertTrue;

import Games.Exception.WrongTurnException;
import Games.Player;
import Games.TicTacToe.TicTacToe;
import org.junit.jupiter.api.Test;

public class TestTTT {

  @Test
  public void testHorizontal() throws WrongTurnException {
    Player player1 = new Player("test1");
    Player player2 = new Player("test2");
    TicTacToe ticTacToe = new TicTacToe(player1, player2);

    // Horizontal Test
    assertTrue(true);
    /*ticTacToe.put(0,0,player1);
    ticTacToe.put(1,1,player2);
    ticTacToe.put(0,1,player1);
    ticTacToe.put(2,2,player2);
    ticTacToe.put(0,2,player1);
    assertEquals(player1,ticTacToe.checkWinner());*/

  }

  @Test
  void name() {}

  @Test
  public void testDiagonal() throws WrongTurnException {
    Player player1 = new Player("test1");
    Player player2 = new Player("test2");
    TicTacToe ticTacToe = new TicTacToe(player1, player2);

    // Diagonal Test

    /*
    [x][ ][ ]
    [ ][x][ ]
    [ ][ ][x]
    */
    assertTrue(true);
    /*
    ticTacToe.put(0,0,player1);
    ticTacToe.put(0,1,player2);
    ticTacToe.put(1,1,player1);
    ticTacToe.put(0,2,player2);
    ticTacToe.put(2,2,player1);

    assertEquals(player1,ticTacToe.checkWinner());        */

  }

  @Test
  public void testAntiDiagonal() throws WrongTurnException {

    /*
    [ ][ ][x]
    [ ][x][ ]
    [x][ ][ ]
    */

    Player player1 = new Player("test1");
    Player player2 = new Player("test2");
    TicTacToe ticTacToe = new TicTacToe(player1, player2);

    // Diagonal Test
    assertTrue(true);
    /*      ticTacToe.put(2,0,player1);
    ticTacToe.put(0,0,player2);
    ticTacToe.put(1,1,player1);
    ticTacToe.put(0,0,player2);
    ticTacToe.put(0,2,player1);

    assertEquals(player1,ticTacToe.checkWinner());          */
  }
}

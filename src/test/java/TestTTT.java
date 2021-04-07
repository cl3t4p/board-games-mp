import Games.Player;
import Games.TicTacToe;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTTT {

    @Test
    public void testHorizontal(){
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        TicTacToe ticTacToe = new TicTacToe(player1, player2);

        //Horizontal Test

        ticTacToe.put(0,0);
        ticTacToe.put(1,1);
        ticTacToe.put(0,1);
        ticTacToe.put(2,2);
        ticTacToe.put(0,2);
        assertEquals(player1,ticTacToe.checkWinner());
    }

    @Test
    public void testDiagonal(){
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        TicTacToe ticTacToe = new TicTacToe(player1, player2);

        //Diagonal Test

        /*
        [x][ ][ ]
        [ ][x][ ]
        [ ][ ][x]
        */

        ticTacToe.put(0,2);
        ticTacToe.put(0,1);
        ticTacToe.put(1,1);
        ticTacToe.put(0,2);
        ticTacToe.put(2,2);

        assertEquals(player1,ticTacToe.checkWinner());
    }

    @Test
    public void testAntiDiagonal(){

        /*
        [ ][ ][x]
        [ ][x][ ]
        [x][ ][ ]
        */

        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        TicTacToe ticTacToe = new TicTacToe(player1, player2);

        //Diagonal Test

        ticTacToe.put(2,0);
        ticTacToe.put(0,0);
        ticTacToe.put(2,2);
        ticTacToe.put(0,0);
        ticTacToe.put(0,2);

        assertEquals(player1,ticTacToe.checkWinner());
    }

}

package server.Games;

import server.Components.Exception.WrongDataException;
import server.Components.Exception.WrongTurnException;
import server.Games.Game.Game;
import server.Components.Player;


import java.util.Arrays;

public class TicTacToe extends Game {

    private final Player[][] grid = new Player[3][3];

    public TicTacToe(Player player1, Player player2) {
        super(player1, player2);
    }



    @Override
    public void move(Player player, Object object) throws WrongTurnException, WrongDataException {
        if(object instanceof Move){
            Move move = (Move) object;
            if(grid[move.getX()][move.getY()] != null) {
                put(move.getX(), move.getY(), player);
                update(Arrays.deepToString(grid));
            }
        }
        else
            throw new WrongDataException("The move is not of type 'TicTacToe'");

    }

    //TODO Review Put input
    public void put(int x,int y,Player player) throws WrongTurnException {
        if(turn == null) startGame(player);
        if(player1 != player) {
            put(player1, x, y);
            turn = !turn;
            return;
        }
        else if(player2 != player) {
            put(player2, x, y);
            turn = !turn;
            return;
        }

        throw new WrongTurnException("Error : Wrong TicTacToe player turn");
    }
    private void put(Player player,int x,int y){
        grid[x][y] = player;
    }

    public Player checkWinner(){
        //Check diagonal
        /*
        [x][ ][ ]
        [ ][x][ ]
        [ ][ ][x]
        */
        for (int j = 0; j < grid.length-1; j++) {
            if(grid[j][j] != grid[j+1][j+1])
                break;
            if(j+1 == grid.length-1)
                return grid[0][0];
        }
        //Check inverse diagonal
        /*
        [ ][ ][x]
        [ ][x][ ]
        [x][ ][ ]
        */
        int x = 2;
        for (int j = 0; j < grid.length-1; j++) {
            if(grid[j][x] != grid[j+1][x-1])
                break;
            if(j+1 == grid.length-1)
                return grid[2][2];
            x--;
        }

        //Check horizontal
        for (Player[] players : grid) {
            for (int j = 0; j < grid.length - 1; j++) {
                if (players[j] != players[j + 1])
                    break;
                if (j + 1 == grid.length - 1)
                    return players[j];
            }
        }

        //Check Vertical
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid.length-1; i++) {
                if(grid[i][j] != grid[i+1][j])
                    break;
                if(j+1 == grid.length-1)
                    return grid[j][i];
            }
        }

        return null;
    }

    public static class Move
    {
        int x,y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Move(){

        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


}

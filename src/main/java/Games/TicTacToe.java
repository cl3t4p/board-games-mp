package Games;

public class TicTacToe{
    private final Player[][] grid = new Player[3][3];
    private final Player player1,player2;
    boolean turn = true;
    public TicTacToe(Player player1,Player player2){
        this.player1 = player1;
        this.player2 = player2;

        System.out.println();
    }

    public void put(int x,int y){
        if(turn)
            put(player1,x,y);
        else
            put(player2,x,y);
        turn = !turn;
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
        for (int j = 0; j < grid.length-1; j++) {
            if(grid[j][j] != grid[j+1][j+1])
                break;
            if(j+1 == grid.length-1)
                return grid[0][0];
        }
        //Change code ^

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


    //Testing
    public Player[][]getGrid(){
        return grid;
    }


}

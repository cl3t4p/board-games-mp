package  com.boardgame.mp.server.games;



import com.boardgame.mp.server.components.Exception.NotFoundByUUID;
import com.boardgame.mp.server.components.Exception.OverlappingException;
import com.boardgame.mp.server.components.Exception.WrongDataException;
import com.boardgame.mp.server.components.Exception.WrongTurnException;
import com.boardgame.mp.server.games.game.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import com.boardgame.mp.server.Repository.GameRepo;
import com.boardgame.mp.server.components.data.Player;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.Collections;


public class TicTacToe extends Game {

    private final Player[][] grid = new Player[3][3];


    private enum GameResponse {
        OK,
    }

    private Player turn;



    public TicTacToe(Player player1, Player player2,GameRepo gameRepo) {
        super(player1, player2,gameRepo);
    }

    @Override
    public ResponseEntity<Object> move(Player player, String object)  {
        ObjectMapper objectMapper = new ObjectMapper();
        Move move;

        try {
            move = objectMapper.readValue(object, Move.class);
        } catch (JsonProcessingException e) {
            throw new WrongDataException("Wrong data format for the move!");
        }



        if(grid[move.getX()][move.getY()] == null) {
                GameResponse message = put(move.getY(), move.getX(), player);
                Player winner = checkWinner();

            System.out.println(winner);
                if(winner != null){
                    update("Winner : "+winner.getPublicuuid().toString());
                    finishGame();
                    return ResponseEntity.ok().body(Collections.singletonMap("response","You are the winner"));
                }

                if(message.equals(GameResponse.OK))
                    update(arrayToString());

                return ResponseEntity.ok().body(Collections.singletonMap("response","The Move has been sent"));
            }else
                throw new OverlappingException("Error Trying to replace a non null spot");


    }

    public String arrayToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (Player[] players : grid) {
            stringBuilder.append(Arrays.toString(players)).append("\n");
        }
        return stringBuilder.toString();
    }

    private GameResponse put(int y,int x,Player player) {
        if(player.equals(player1) || player.equals(player2)) {
            if (turn == null) {
                turn = player;
                grid[x][y] = turn;
                return GameResponse.OK;
            }
            if(!turn.equals(player)){
                turn = player;
                grid[x][y] = turn;
                return GameResponse.OK;
            }
            else
                throw new WrongTurnException("Error : Wrong TicTacToe player turn");
        }
        throw new NotFoundByUUID("Error : User not registered to this game");
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
                return grid[0][2];
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



    @Data
    public static class Move
    {
        int x,y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Move() {
        }
    }




}

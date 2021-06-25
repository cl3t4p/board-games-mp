package com.boardgame.mp.server.games;

import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.components.exception.WrongDataException;
import com.boardgame.mp.server.games.game.Game;
import com.boardgame.mp.server.repository.GameRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class RockPaperScissors extends Game {

    private static final int MAX_TURN = 3;
    private int turn = 0;
    private Player[] players = new Player[MAX_TURN];

    private Player first = null;
    private RPSMove firstMove;


    public RockPaperScissors(Player player1, Player player2, GameRepo gameRepo) {
        super(player1, player2, gameRepo);
    }

    @Override
    public ResponseEntity<Object> move(Player player, String object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Move move;

        try {
            move = objectMapper.readValue(object, Move.class);
        } catch (JsonProcessingException e) {
            throw new WrongDataException("Wrong data format for the move!");
        }


        if (first == null) {
          first = player;
          firstMove = move.getGameMove();
          return ResponseEntity.ok().body(Collections.singletonMap("response","Ok"));
        }else{
            Player winner = checkWinner(move.getGameMove());
            if(winner == null){
                update("Draw");
                return ResponseEntity.ok().body(Collections.singletonMap("response","Draw"));
            }else{
                turn++;
                update("Winner is "+ winner.safeData());
                return ResponseEntity.ok().body(Collections.singletonMap("response","You "+ (player == winner ? "win!":"lost!")));
            }
        }
    }

    private Player checkWinner(RPSMove rpsMove){
        RPSMove result = firstMove.isWinner(rpsMove);
        if(result == null) return null;

        if(result == firstMove)
            return first;
        else
            return player1 == first ? player2 : player1;

    }

    @Override
    public ResponseEntity<Object> getMoveInfo() {
        return ResponseEntity.ok().body(RPSMove.values());
    }


    @AllArgsConstructor
    @FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
    private enum RPSMove {
        ROCK(2,1),
        PAPER(0,2),
        SCISSORS(1,0);
        int winto,loseto;

        private RPSMove isWinner(RPSMove lastMove){
            List<RPSMove> list = List.of(RPSMove.values());
            int pos = list.indexOf(lastMove);
            if(this.winto == pos)
                return this;
            else if(this.loseto == pos)
                return lastMove;
            else
                return null;
        }
    }

    @Data
    public static class Move {

        private RPSMove gameMove;

        public Move(RPSMove gameMove) {
            this.gameMove = gameMove;
        }

        public Move() {}
    }


}

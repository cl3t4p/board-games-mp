package server.Games.Components.Game;

import server.Games.Components.Exception.WrongDataException;
import server.Games.Components.Exception.WrongTurnException;
import server.Games.Components.Player;

public abstract class Game {

    protected final Player player1,player2;

    protected Boolean turn = null;
    public Game(Player player1,Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    protected void startGame(Player first){
        turn = (player1 == first);
    }


    public abstract Player checkWinner();

    public abstract void move(Player player,Object move) throws WrongTurnException, WrongDataException;

}

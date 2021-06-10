package server.Games.Game;

import org.springframework.web.socket.TextMessage;
import server.Controller.WebSocket.WebSocketGameController;
import server.Components.Exception.WrongDataException;
import server.Components.Exception.WrongTurnException;
import server.Components.Player;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Game implements Move {

    private static final List<Game> games = new CopyOnWriteArrayList<>();

    public static List<Game> getGames() {
        return games;
    }


    private final UUID uuid;

    protected final Player player1,player2;

    protected Boolean turn = null;

    public Game(Player player1,Player player2){
        this.uuid = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        Game.getGames().add(this);
    }

    protected void startGame(Player first){
        turn = (player1 == first);
    }

    public UUID getUUID() {
        return uuid;
    }

    public abstract Player checkWinner();

    protected void update(String data){
        WebSocketGameController.getSessions()
                .stream()
                .filter(session -> session.getGameid().equals(uuid))
                .forEach(session -> {
                    try {
                        session.getWebSocketSession().sendMessage(new TextMessage(data));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    protected void close(){
        WebSocketGameController.getSessions()
                .stream()
                .filter(session -> session.getGameid().equals(uuid))
                .forEach(session -> {
                    try {
                        session.getWebSocketSession().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public abstract void move(Player player,Object move) throws WrongTurnException, WrongDataException;

}

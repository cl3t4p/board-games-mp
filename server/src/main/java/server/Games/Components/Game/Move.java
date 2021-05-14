package server.Games.Components.Game;

import server.Games.Components.Exception.WrongDataException;
import server.Games.Components.Exception.WrongTurnException;
import server.Games.Components.Player;

public interface Move {
    void move(Player player, Object move) throws WrongTurnException, WrongDataException;
}

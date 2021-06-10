package server.Games.Game;

import server.Components.Exception.WrongDataException;
import server.Components.Exception.WrongTurnException;
import server.Components.Player;

public interface Move {
    void move(Player player, Object move) throws WrongTurnException, WrongDataException;
}

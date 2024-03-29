package com.boardgame.mp.server.games.game;

import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.repository.GameRepo;
import lombok.EqualsAndHashCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;


/**
 * Important !!
 * Remember to add an Empty Constructor
 */
@EqualsAndHashCode
public abstract class Game{

  private static GameRepo gameRepo;
  private UUID uuid;
  protected Player player1, player2;

  public Game() {
  }

  public Game(Player player1, Player player2, GameRepo gameRepo) {
    this.uuid = UUID.randomUUID();
    this.player1 = player1;
    this.player2 = player2;
    if (Game.gameRepo == null) Game.gameRepo = gameRepo;
    gameRepo.add(this);
  }

  /**
   * Called when someone send a command throuh the api
   *
   * @param player The Player who sent the command
   * @param object Command in JSON Format
   * @return Response that will be sent to the player
   */
  public abstract ResponseEntity<Object> move(Player player, String object) throws Exception;

  public UUID getUUID() {
    return uuid;
  }

  /** This function send Data to the connected websocket */
  protected void update(String data) {
    gameRepo.updateByUUID(data, uuid);
  }

  /** Close all websocket active WebSocket */
  protected void finishGame() {
    gameRepo.closeByUUID(uuid);
    gameRepo.remove(this);
  }
  public abstract ResponseEntity<Object> getMoveInfo();
}

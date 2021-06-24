package com.boardgame.mp.server.controller;

import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.components.exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.repository.PlayerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

  private final PlayerRepo playerRepo;

  /**
   * It's a method for checking a Player Name By PublicUUID
   *
   * @param uuid Player pubblicUUID
   * @return Player name + pubblicUUID
   */
  @GetMapping("/")
  public ResponseEntity<Object> getPlayerName(@RequestParam UUID uuid) {
    Player player = playerRepo.getPlayerByPublicuuid(uuid).orElseThrow(PlayerNotFoundByUUID::new);
    return ResponseEntity.ok().body(player.safeData());
  }

  /**
   * Delete Player
   *
   * @param uuid Player privateUUID
   * @return The Deleted Player
   */
  @DeleteMapping("/")
  public ResponseEntity<Object> delPlayer(@RequestParam String uuid) throws PlayerNotFoundByUUID {
    Player player =
        playerRepo
            .getPlayerByPrivateuuid(UUID.fromString(uuid))
            .orElseThrow(PlayerNotFoundByUUID::new);
    playerRepo.delete(player);
    return ResponseEntity.ok().body(player);
  }

  /**
   * Create a player by passing the name
   *
   * @param name Name of the new Player
   * @return The new Player
   */
  @PostMapping("/")
  public Player newPlayer(@RequestParam String name) {
    Player player = new Player(name);
    playerRepo.save(player);
    return player;
  }
}

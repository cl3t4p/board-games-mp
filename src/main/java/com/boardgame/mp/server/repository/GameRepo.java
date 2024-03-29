package com.boardgame.mp.server.repository;

import com.boardgame.mp.server.games.game.Game;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepo {

  void remove(Game game);

  void add(Game game);

  void updateByUUID(String data, UUID uuid);

  Optional<Game> GameByUUID(UUID uuid);

  void closeByUUID(UUID uuid);

  boolean existsByUUID(UUID uuid);
}

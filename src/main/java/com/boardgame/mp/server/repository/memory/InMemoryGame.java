package com.boardgame.mp.server.repository.memory;

import com.boardgame.mp.server.games.game.Game;
import com.boardgame.mp.server.repository.GameRepo;
import com.boardgame.mp.server.repository.SessionRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class InMemoryGame implements GameRepo {

  private final SessionRepo sessionRepo;

  HashSet<Game> games = new HashSet<>();

  public InMemoryGame(SessionRepo sessionRepo) {
    this.sessionRepo = sessionRepo;
  }

  @Override
  public void remove(Game game) {
    games.remove(game);
  }

  @Override
  public void add(Game game) {
    games.add(game);
  }

  @Override
  public void closeByUUID(UUID uuid) {
    sessionRepo
        .sessionsByGameUUID(uuid)
        .forEach(
            session -> {
              try {
                session.getWebSocketSession().close();
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
  }

  @Override
  public void updateByUUID(String data, UUID uuid) {
    sessionRepo
        .sessionsByGameUUID(uuid)
        .forEach(
            session -> {
              try {
                session.getWebSocketSession().sendMessage(new TextMessage(data));
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
  }

  @Override
  public boolean existsByUUID(UUID uuid) {
    return games.stream().anyMatch(game -> game.getUUID().equals(uuid));
  }

  @Override
  public Optional<Game> GameByUUID(UUID uuid) {
    return games.stream().filter(game -> game.getUUID().equals(uuid)).findFirst();
  }
}

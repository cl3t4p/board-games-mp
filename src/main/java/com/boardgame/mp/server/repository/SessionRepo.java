package com.boardgame.mp.server.repository;

import com.boardgame.mp.server.components.data.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.UUID;
@Repository
public interface SessionRepo {

  void add(Session session);

  void removeIfWebSocketEquals(WebSocketSession session);

  Set<Session> sessionsByGameUUID(UUID uuid);

  Set<Session> getAllSession();

  boolean existsSessionByGameUUID(UUID uuid);
}

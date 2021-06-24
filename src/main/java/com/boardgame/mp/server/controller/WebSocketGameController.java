package com.boardgame.mp.server.controller;

import com.boardgame.mp.server.components.data.Session;
import com.boardgame.mp.server.repository.GameRepo;
import com.boardgame.mp.server.repository.SessionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class WebSocketGameController extends TextWebSocketHandler {
  private final SessionRepo sessionRepo;
  private final GameRepo gameRepo;

  /**
   * When a somone try to connect through websocket this function will be called
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession webSession) throws IOException {

    //Remove '/games/' from the Uri for getting the GameUUID
    String game_id = webSession.getUri().getPath().replaceFirst("/games/", "");
    UUID uuid = UUID.fromString(game_id);

    //Check if a Game exists or close the connection
    if (!gameRepo.existsByUUID(uuid)) {
      webSession.sendMessage(new TextMessage("There is no game with that UUID"));
      webSession.close();
      return;
    }

    Session session = new Session(webSession, UUID.fromString(game_id));
    sessionRepo.add(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    try {
      session.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    sessionRepo.removeIfWebSocketEquals(session);
  }
}

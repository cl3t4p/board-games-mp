package server.Controller.WebSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import server.Games.Game.Game;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WebSocketGameController extends TextWebSocketHandler {

    private final static Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);
    private final static List<Session> sessions = new CopyOnWriteArrayList<>();

    public static List<Session> getSessions() {
        return sessions;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSession) {
        logger.debug("Connected : " + webSession);
        String game_id = webSession.getUri().getPath().replaceFirst("/game/","");
        UUID uuid = UUID.fromString(game_id);

        if(Game.getGames().stream().noneMatch(game -> game.getUUID().equals(uuid)))
            return;

        Session session = new Session(webSession,UUID.fromString(game_id));
        sessions.add(session);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("Cannot close session on afterConnectionClosed ", e);
        }
        sessions.removeIf(session1 -> session1.webSocketSession.equals(session));
    }





    public static class Session{
        final private String session_id;
        final private WebSocketSession webSocketSession;
        final private UUID gameid;

        public Session(WebSocketSession webSocketSession, UUID gameid) {
            this.webSocketSession = webSocketSession;
            this.gameid = gameid;
            this.session_id = webSocketSession.getId();
        }


        public String getSession_id() {
            return session_id;
        }

        public WebSocketSession getWebSocketSession() {
            return webSocketSession;
        }

        public UUID getGameid() {
            return gameid;
        }

    }
}

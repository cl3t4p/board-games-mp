package  com.boardgame.mp.server.controller;

import com.boardgame.mp.server.Repository.GameRepo;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.boardgame.mp.server.Repository.SessionRepo;
import com.boardgame.mp.server.components.data.Session;

import java.io.IOException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class WebSocketGameController extends TextWebSocketHandler {
    private final static Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);
    private final SessionRepo sessionRepo;
    private final GameRepo gameRepo;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSession) throws IOException {
        logger.debug("Connected : " + webSession);
        String game_id = webSession.getUri().getPath().replaceFirst("/games/","");
        UUID uuid = UUID.fromString(game_id);

        if(!gameRepo.existsByUUID(uuid)) {
            webSession.sendMessage(new TextMessage("There is no game with that UUID"));
            webSession.close();
            return;
        }

        Session session = new Session(webSession,UUID.fromString(game_id));
        sessionRepo.add(session);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, @NotNull CloseStatus status) {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("Cannot close session on afterConnectionClosed ", e);
        }
        sessionRepo.removeIfWebSocketEquals(session);
    }







}

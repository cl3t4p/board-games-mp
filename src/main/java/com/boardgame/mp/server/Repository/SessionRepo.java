package  com.boardgame.mp.server.Repository;


import com.boardgame.mp.server.components.data.Session;
import org.springframework.web.socket.WebSocketSession;


import java.util.Set;
import java.util.UUID;

public interface SessionRepo {
    void add(Session session);

    void removeIfWebSocketEquals(WebSocketSession session);

    Set<Session> sessionsByGameUUID(UUID uuid);

    Set<Session> getAllSession();

    boolean existsSessionByGameUUID(UUID uuid);


}

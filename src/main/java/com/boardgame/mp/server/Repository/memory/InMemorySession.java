package  com.boardgame.mp.server.Repository.memory;


import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import com.boardgame.mp.server.Repository.SessionRepo;
import com.boardgame.mp.server.components.data.Session;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InMemorySession implements SessionRepo {
    HashSet<Session> sessions = new HashSet<>();
    @Override
    public void add(Session session) {
        sessions.add(session);
    }

    @Override
    public void removeIfWebSocketEquals(WebSocketSession session) {
        sessions.removeIf(session1 -> session1.getWebSocketSession().equals(session));
    }

    @Override
    public HashSet<Session> getAllSession(){
        return (HashSet<Session>) sessions.clone();
    }

    @Override
    public boolean existsSessionByGameUUID(UUID uuid) {
        return sessions.stream().anyMatch(session -> session.getGameid().equals(uuid));
    }

    @Override
    public Set<Session> sessionsByGameUUID(UUID uuid) {
        return sessions.stream().filter(session -> session.getGameid().equals(uuid)).collect(Collectors.toSet());
    }


}

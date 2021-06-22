package  com.boardgame.mp.server.components.data;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.socket.WebSocketSession;
import java.util.UUID;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Session {

    String session_id;
    WebSocketSession webSocketSession;
    UUID gameid;

    public Session(@NotNull WebSocketSession webSocketSession, UUID gameid) {
        this.webSocketSession = webSocketSession;
        this.gameid = gameid;
        this.session_id = webSocketSession.getId();
    }

}

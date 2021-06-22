package  com.boardgame.mp.server.components.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundByUUID extends RuntimeException {
    public PlayerNotFoundByUUID() {
        super("The Player with that uuid has not been found");
    }


}

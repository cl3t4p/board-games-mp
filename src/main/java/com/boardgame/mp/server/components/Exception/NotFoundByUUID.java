package  com.boardgame.mp.server.components.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundByUUID extends RuntimeException{


    public NotFoundByUUID(String s) {
        super(s);
    }
}

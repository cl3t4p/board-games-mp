package  com.boardgame.mp.server.components.Exception;

public class OverlappingException extends RuntimeException{
    public OverlappingException() {
    }

    public OverlappingException(String message) {
        super(message);
    }
}

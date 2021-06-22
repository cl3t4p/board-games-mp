package  com.boardgame.mp.server.components.Exception;

public class WrongTurnException extends RuntimeException{
    public WrongTurnException() {
    }

    public WrongTurnException(String message) {
        super(message);
    }
}

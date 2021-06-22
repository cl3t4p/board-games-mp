package  com.boardgame.mp.server.components.Exception;

public class WrongDataException extends RuntimeException {
    public WrongDataException() {
    }

    public WrongDataException(String message) {
        super(message);
    }
}

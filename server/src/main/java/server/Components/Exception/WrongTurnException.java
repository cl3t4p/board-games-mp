package server.Components.Exception;

public class WrongTurnException extends Exception{

    public WrongTurnException(String message) {
        super(message);
    }
}
package com.boardgame.mp.server.components.exception;

public class WrongTurnException extends RuntimeException {
  public WrongTurnException() {}

  public WrongTurnException(String message) {
    super(message);
  }
}

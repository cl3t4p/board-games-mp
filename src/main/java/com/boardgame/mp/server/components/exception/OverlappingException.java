package com.boardgame.mp.server.components.exception;

public class OverlappingException extends RuntimeException {
  public OverlappingException() {}

  public OverlappingException(String message) {
    super(message);
  }
}

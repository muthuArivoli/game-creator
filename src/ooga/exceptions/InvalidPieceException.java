package ooga.exceptions;

public class InvalidPieceException extends Exception {
  public InvalidPieceException(String errorMsg, Throwable error) {
    super(errorMsg, error);
  }
}

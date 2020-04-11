package ooga.exceptions;

public class InvalidGridException extends Exception{
  public InvalidGridException(String errorMsg, Throwable error) {
    super(errorMsg, error);
  }
}

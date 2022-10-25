package exceptions.services;

public class IdNullPointerException extends RuntimeException {
  public IdNullPointerException() {
    super("Id can not be null.");
  }
}

package exceptions.services;

public class NullEntityException extends RuntimeException {
  public NullEntityException() {
    super("Entity can not be null");
  }
}

package exceptions.ioc;

public class IocDuplicatedKeyException extends RuntimeException {
  public IocDuplicatedKeyException() {
    super("Key already exists");
  }
}

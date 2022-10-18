package exceptions.ioc;

public class IocKeyNullPointerException extends RuntimeException {
  public IocKeyNullPointerException() {
    super("Key can not be null");
  }
}

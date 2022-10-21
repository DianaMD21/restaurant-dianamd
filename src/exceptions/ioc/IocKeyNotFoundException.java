package exceptions.ioc;

public class IocKeyNotFoundException extends RuntimeException {
  public IocKeyNotFoundException() {
    super("Key not found");
  }
}

package exceptions.ioc;

public class IocValueNullPointerException extends RuntimeException {
  public IocValueNullPointerException() {
    super("Value can not be null");
  }
}

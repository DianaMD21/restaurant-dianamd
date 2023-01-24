package com.diana.restaurant.exceptions.ioc;

public class IocKeyNullPointerException extends RuntimeException {
  public IocKeyNullPointerException() {
    super("Key can not be null");
  }
}

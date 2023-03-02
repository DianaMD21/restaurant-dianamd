package com.diana.restaurant.exceptions;

public class KeyNotFoundException extends RuntimeException {
  public KeyNotFoundException(String entity) {
    super(entity + " key not found");
  }
}

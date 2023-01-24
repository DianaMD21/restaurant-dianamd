package com.diana.restaurant.exceptions.services;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class<?> entity, Object id) {
    super(entity.toString() + " entity by id: " + id + " not found.");
  }
}

package exceptions.services;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class entity, Long id) {
    super(entity.toString() + " entity by id: " + id + " not found.");
  }
}

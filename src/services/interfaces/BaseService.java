package services.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<E, K> {
  Optional<E> insert(E entity);

  Optional<E> findById(K id);

  Optional<E> delete(K id);

  Optional<E> update(E entity);

  Collection<E> findAll();
}

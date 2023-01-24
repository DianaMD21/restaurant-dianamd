package com.diana.restaurant.services.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<E, K> {
  E insert(E entity);

  Optional<E> findById(K id);

  E delete(K id);

  E update(E entity);

  Collection<E> findAll();
}

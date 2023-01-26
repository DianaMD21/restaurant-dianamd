package com.diana.restaurant.services.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<E, K> {

  Optional<E> findById(K id);

  E delete(K id);

  E update(E entity);

  Collection<E> getAll();

  E add(E entity);
}

package services.implementation;

import entities.BaseEntity;
import enums.StatusEnum;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import services.interfaces.BaseService;
import util.BaseEntityUtil;

public abstract class BaseServiceImpl<E extends BaseEntity<K>, K extends Serializable>
    implements BaseService<E, K> {
  protected final List<E> entities;
  protected final Class<E> entityClass;
  protected final Class<K> keyClass;
  protected K currentId;
  private final Function<K, K> generateNextId;
  private final BiFunction<E, E, E> fetchEntity;

  public BaseServiceImpl(K currentId, Class<E> entityClass, Class<K> keyClass) {
    this.entities = new ArrayList<>();
    this.entityClass = entityClass;
    this.currentId = currentId;
    this.keyClass = keyClass;
    this.generateNextId =
        (Function<K, K>) BaseEntityUtil.ENTITY_KEY_TO_GENERATOR_FUNCTION_MAP.get(this.keyClass);
    this.fetchEntity =
        (BiFunction<E, E, E>) BaseEntityUtil.ENTITY_TO_FETCH_FUNCTION_MAP.get(this.entityClass);
  }

  @Override
  public Optional<E> insert(E entity) {
    entity.setId(this.currentId);
    entity.setStatus(StatusEnum.ACTIVE);
    entity.setCreatedAt(Instant.now());
    entity.setUpdatedAt(Instant.now());
    this.currentId = this.generateNextId.apply(this.currentId);
    this.entities.add(entity);
    return Optional.of(entity);
  }

  @Override
  public Optional<E> findById(K id) {
    Optional.ofNullable(id).orElseThrow(IdNullPointerException::new);
    var client =
        this.entities.stream()
            .filter(c -> id.equals(c.getId()) && !(c.getStatus() == StatusEnum.DELETED))
            .findAny()
            .orElseThrow(() -> new EntityNotFoundException(entityClass, id));
    return Optional.ofNullable(client);
  }

  @Override
  public Optional<E> delete(K id) {
    Optional.ofNullable(id).orElseThrow(IdNullPointerException::new);
    var client = this.findById(id).get();
    client.setStatus(StatusEnum.DELETED);
    client.setUpdatedAt(Instant.now());
    return Optional.of(client);
  }

  @Override
  public Optional<E> update(E entity) {
    Optional.ofNullable(entity).orElseThrow(NullEntityException::new);
    Optional.ofNullable(entity.getId()).orElseThrow(IdNullPointerException::new);
    var existingEntity = this.findById(entity.getId()).get();
    entity.setUpdatedAt(Instant.now());
    var updatedEntity = fetchEntity.apply(existingEntity, entity);
    return Optional.of(updatedEntity);
  }

  @Override
  public List<E> findAll() {
    return this.entities.stream()
        .filter(c -> c.getStatus() != StatusEnum.DELETED)
        .collect(Collectors.toList());
  }
}

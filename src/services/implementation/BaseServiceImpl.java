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
  public E insert(E entity) {
    entity.setId(this.currentId);
    entity.setStatus(StatusEnum.ACTIVE);
    entity.setCreatedAt(Instant.now());
    entity.setUpdatedAt(Instant.now());
    this.currentId = this.generateNextId.apply(this.currentId);
    this.entities.add(entity);
    return entity;
  }

  @Override
  public Optional<E> findById(K id) {
    Optional.ofNullable(id).orElseThrow(IdNullPointerException::new);
    var entity =
        this.entities.stream()
            .filter(e -> id.equals(e.getId()) && !(e.getStatus() == StatusEnum.DELETED))
            .findAny()
            .orElseThrow(() -> new EntityNotFoundException(entityClass, id));
    return Optional.ofNullable(entity);
  }

  @Override
  public E delete(K id) {
    Optional.ofNullable(id).orElseThrow(IdNullPointerException::new);
    var entity = this.findById(id).get();
    entity.setStatus(StatusEnum.DELETED);
    entity.setUpdatedAt(Instant.now());
    return entity;
  }

  @Override
  public E update(E entity) {
    Optional.ofNullable(entity).orElseThrow(NullEntityException::new);
    Optional.ofNullable(entity.getId()).orElseThrow(IdNullPointerException::new);
    var existingEntity = this.findById(entity.getId()).get();
    entity.setUpdatedAt(Instant.now());
    var updatedEntity = fetchEntity.apply(existingEntity, entity);
    return updatedEntity;
  }

  @Override
  public List<E> findAll() {
    return this.entities.stream()
        .filter(e -> e.getStatus() != StatusEnum.DELETED)
        .collect(Collectors.toList());
  }
}

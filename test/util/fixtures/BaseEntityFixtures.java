package util.fixtures;

import entities.BaseEntity;
import java.time.Instant;
import java.util.Optional;

public final class BaseEntityFixtures {
  public static BaseEntity buildBaseEntity(BaseEntity baseEntity, BaseEntity baseEntityExample) {
    baseEntity.setId(Optional.ofNullable(baseEntityExample).map(BaseEntity::getId).orElse(null));
    baseEntity.setUpdatedby(
        Optional.ofNullable(baseEntityExample).map(BaseEntity::getUpdatedby).orElse(1L));
    baseEntity.setCreatedBy(
        Optional.ofNullable(baseEntityExample).map(BaseEntity::getCreatedBy).orElse(1L));
    baseEntity.setUpdatedAt(
        Optional.ofNullable(baseEntityExample).map(BaseEntity::getUpdatedAt).orElse(Instant.now()));
    baseEntity.setCreatedAt(
        Optional.ofNullable(baseEntityExample).map(BaseEntity::getCreatedAt).orElse(Instant.now()));
    return baseEntity;
  }
}

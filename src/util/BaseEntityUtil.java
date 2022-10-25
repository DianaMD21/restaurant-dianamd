package util;

import entities.BaseEntity;
import java.util.Optional;

public final class BaseEntityUtil {
  public static BaseEntity fetchBaseEntity(BaseEntity target, BaseEntity source) {
    target.setUpdatedAt(
        Optional.ofNullable(source).map(BaseEntity::getUpdatedAt).orElse(target.getUpdatedAt()));
    target.setCreatedAt(
        Optional.ofNullable(source).map(BaseEntity::getCreatedAt).orElse(target.getCreatedAt()));
    target.setCreatedBy(
        Optional.ofNullable(source).map(BaseEntity::getCreatedBy).orElse(target.getCreatedBy()));
    target.setUpdatedby(
        Optional.ofNullable(source).map(BaseEntity::getUpdatedby).orElse(target.getUpdatedby()));
    target.setStatus(
        Optional.ofNullable(source).map(BaseEntity::getStatus).orElse(target.getStatus()));
    return target;
  }
}

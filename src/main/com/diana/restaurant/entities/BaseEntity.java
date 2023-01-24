package com.diana.restaurant.entities;

import com.diana.restaurant.enums.StatusEnum;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public abstract class BaseEntity<T extends Serializable> {
  private T id;
  private T updatedBy;
  private T createdBy;
  private StatusEnum status;
  private Instant createdAt;
  private Instant updatedAt;

  public BaseEntity() {}

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  public T getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(T updatedBy) {
    this.updatedBy = updatedBy;
  }

  public T getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(T createdBy) {
    this.createdBy = createdBy;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseEntity<?> that = (BaseEntity<?>) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

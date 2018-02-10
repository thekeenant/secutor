package com.keenant.secutor.event;

import com.keenant.secutor.engine.model.Entity;
import java.util.UUID;

public abstract class EntityEvent<T extends Entity> implements Event {
  private final T entity;

  public EntityEvent(T entity) {
    this.entity = entity;
  }

  public T getEntity() {
    return entity;
  }

  public UUID getEntityUuid() {
    return entity.getUuid();
  }
}

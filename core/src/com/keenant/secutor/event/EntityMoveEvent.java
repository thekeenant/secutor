package com.keenant.secutor.event;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;

public class EntityMoveEvent<T extends Entity> extends EntityEvent<T> {
  private final Vector2 from;
  private final Vector2 to;

  public EntityMoveEvent(T entity, Vector2 from, Vector2 to) {
    super(entity);
    this.from = from;
    this.to = to;
  }

  public Vector2 getFrom() {
    return from;
  }

  public Vector2 getTo() {
    return to;
  }
}

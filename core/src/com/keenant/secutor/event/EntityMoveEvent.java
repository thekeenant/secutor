package com.keenant.secutor.event;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;

public class EntityMoveEvent<T extends Entity> extends EntityEvent<T> {
  private final Vector2 movement;

  public EntityMoveEvent(T entity, Vector2 movement) {
    super(entity);
    this.movement = movement;
  }

  public Vector2 getMovement() {
    return movement;
  }
}

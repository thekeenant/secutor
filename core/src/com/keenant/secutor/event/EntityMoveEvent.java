package com.keenant.secutor.event;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.utils.Direction;

/**
 * An entity moves from one point to another.
 * @param <T> the type of entity that moved
 */
public class EntityMoveEvent<T extends Entity> extends EntityEvent<T> {
  private final Vector2 from;
  private final Vector2 to;
  private final Direction facing;

  public EntityMoveEvent(T entity, Vector2 from, Vector2 to, Direction facing) {
    super(entity);
    this.from = from;
    this.to = to;
    this.facing = facing;
  }

  public Vector2 getFrom() {
    return from;
  }

  public Vector2 getTo() {
    return to;
  }

  public Direction getFacing() {
    return facing;
  }
}

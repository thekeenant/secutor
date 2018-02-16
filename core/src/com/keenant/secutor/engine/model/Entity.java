package com.keenant.secutor.engine.model;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.EntityController;
import java.util.UUID;

/**
 * An entity in the game that has a position.
 */
public interface Entity extends Model {

  /**
   * @return the UUID for this entity
   */
  UUID getUuid();

  /**
   * @return the modifiable position of this entity in the world
   */
  Vector2 getPosition();

  /**
   * @return the x position of this entity
   */
  default float getX() {
    return getPosition().x;
  }

  /**
   * @return the y position of this entity
   */
  default float getY() {
    return getPosition().y;
  }

  @Override
  EntityController<?, ?> createController();
}

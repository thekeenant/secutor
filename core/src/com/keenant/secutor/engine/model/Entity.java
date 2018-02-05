package com.keenant.secutor.engine.model;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.EntityController;
import java.util.UUID;

/**
 * An entity in the game that has a position.
 */
public interface Entity extends Model {
  UUID getUuid();

  Vector2 getPosition();

  default float getX() {
    return getPosition().x;
  }

  default float getY() {
    return getPosition().y;
  }

  @Override
  EntityController<?, ?> createController();
}

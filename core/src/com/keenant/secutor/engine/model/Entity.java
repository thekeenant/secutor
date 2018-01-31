package com.keenant.secutor.engine.model;

import com.badlogic.gdx.math.Vector2;

/**
 * An entity in the game that has a position.
 */
public interface Entity extends Model {
  Vector2 getPosition();

  default float getX() {
    return getPosition().x;
  }

  default float getY() {
    return getPosition().y;
  }
}

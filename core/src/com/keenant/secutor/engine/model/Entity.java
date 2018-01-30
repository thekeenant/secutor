package com.keenant.secutor.engine.model;

import com.badlogic.gdx.math.Vector2;

public interface Entity extends Model {
  Vector2 getPosition();

  default float getX() {
    return getPosition().x;
  }

  default float getY() {
    return getPosition().y;
  }
}

package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;

public class Head implements Entity {
  private final Vector2 position;

  public Head() {
    position = new Vector2();
  }

  @Override
  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(float x, float y) {
    position.x = x;
    position.y = y;
  }
}

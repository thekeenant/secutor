package com.keenant.secutor.world;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
  UP,
  RIGHT,
  LEFT,
  DOWN;

  public static Direction fromVector(Vector2 movement) {
    movement.nor();

    // we weigh up/down movements more
    if (Math.abs(movement.x) < 0.8F) {
      if (movement.y < 0)
        return DOWN;
      else if (movement.y > 0)
        return UP;
    }
    else {
      if (movement.x < 0)
        return LEFT;
      else if (movement.x > 0)
        return RIGHT;
    }

    // if (0, 0):
    return DOWN;
  }
}

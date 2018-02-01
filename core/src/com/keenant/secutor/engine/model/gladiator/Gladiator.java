package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.world.Direction;

public class Gladiator implements Entity {
  private final World world;
  private Vector2 position;

  private float health = 20;

  public Gladiator(World world) {
    this.world = world;
    position = new Vector2(0, 0);
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(float x, float y) {
    position.x = x;
    position.y = y;
  }

  public World getWorld() {
    return world;
  }

  public float getHealth() {
    return health;
  }
}

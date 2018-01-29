package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.head.Head;
import com.keenant.secutor.world.Direction;

public class Gladiator implements Entity {
  private final Head head;

  private Vector2 position;
  private Direction facing = Direction.DOWN;
  private boolean running;

  public Gladiator() {
    head = new Head();
    position = new Vector2(0, 0);
  }

  public Head getHead() {
    return head;
  }

  public Vector2 getPosition() {
    return position;
  }

  public float getX() {
    return position.x;
  }

  public float getY() {
    return position.y;
  }

  public void setPosition(float x, float y) {
    position.x = x;
    position.y = y;
  }

  public Direction getFacing() {
    return facing;
  }

  public void setFacing(Direction facing) {
    this.facing = facing;
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }
}

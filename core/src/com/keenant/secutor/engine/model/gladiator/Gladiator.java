package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.CollidableEntity;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.utils.Direction;

public class Gladiator implements Entity, CollidableEntity {
  private final World world;
  private Vector2 position;

  /** walking/running movement velocity */
  private Vector2 movement = new Vector2();

  /** any additional forces are applied via velocity */
  private Vector2 velocity = new Vector2();
  private Rectangle boundingBox = new Rectangle();
  private Direction facing = Direction.DOWN;

  public Gladiator(World world) {
    this.world = world;
    position = new Vector2(0, 0);
  }

  public float getSpeed() {
    return 2.5F;
  }

  public Vector2 getMovement() {
    return movement;
  }

  public void setMovement(float x, float y) {
    movement.x = x;
    movement.y = y;
  }

  public Vector2 getVelocity() {
    return velocity;
  }

  public void setVelocity(float x, float y) {
    velocity.x = x;
    velocity.y = y;
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

  public Direction getFacing() {
    return facing;
  }

  public void setFacing(Direction facing) {
    this.facing = facing;
  }

  public void setBoundingBox(Rectangle boundingBox) {
    this.boundingBox = boundingBox;
  }

  @Override
  public Rectangle getBoundingBox() {
    return boundingBox;
  }

  public Vector2 center() {
    Vector2 center = new Vector2();
    boundingBox.getCenter(center);
    return center;
  }

  public float getHealth() {
    return 20.0F;
  }

  public float getMaxHealth() {
    return 35.0F;
  }
}

package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.CollidableEntity;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.world.Direction;

public class Gladiator implements Entity, CollidableEntity {
  private final World world;
  private Vector2 position;
  private Rectangle boundingBox = new Rectangle();
  private Vector2 lastMovement = new Vector2();
  private Direction facing = Direction.DOWN;

  private float speed = 2F;

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

  public Vector2 getLastMovement() {
    return lastMovement;
  }

  public void setLastMovement(float x, float y) {
    lastMovement.x = x;
    lastMovement.y = y;
    facing = Direction.fromVector(lastMovement.cpy());
  }

  public Direction getFacing() {
    return facing;
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

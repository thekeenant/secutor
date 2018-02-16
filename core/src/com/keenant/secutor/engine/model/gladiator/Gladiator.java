package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.gladiator.GladiatorController;
import com.keenant.secutor.engine.model.CollidableEntity;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.utils.Direction;
import java.util.UUID;

public class Gladiator implements Entity, CollidableEntity {
  private final World world;
  private final UUID uuid;
  private final String name;
  private Vector2 position = new Vector2();

  /** walking/running movement velocity */
  private Vector2 movement = new Vector2();

  /** any additional forces are applied via velocity */
  private Vector2 velocity = new Vector2();

  private Rectangle boundingBox = new Rectangle();
  private Direction facing = Direction.DOWN;

  public Gladiator(World world, UUID uuid, String name) {
    this.world = world;
    this.uuid = uuid;
    this.name = name;
  }

  public float getSpeed() {
    return 2.5F;
  }

  public Vector2 getMovement() {
    return movement;
  }

  public void setMovement(float x, float y) {
    movement.set(x, y);
  }

  public Vector2 getVelocity() {
    return velocity;
  }

  public void setVelocity(float x, float y) {
    velocity.set(x, y);
    if (velocity.isZero(0.1F)) {
      velocity.setZero();
    }
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(float x, float y) {
    position.set(x, y);
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
  
  @Override
  public Rectangle getBoundingBox() {
    return boundingBox;
  }

  @Override
  public GladiatorController createController() {
    return new GladiatorController<>(this);
  }

  public String getName() {
    return name;
  }

  public UUID getUuid() {
    return uuid;
  }
}

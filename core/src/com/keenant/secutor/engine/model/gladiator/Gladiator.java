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

  private boolean attacking;
  private float attackingTime;

  private float health = getMaxHealth();

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

  public void setHealth(float health) {
    this.health = health;
  }

  public float getHealth() {
    return health;
  }

  public float getMaxHealth() {
    return 100F;
  }

  public void addVelocity(float x, float y) {
    velocity.add(x, y);
  }

  public boolean isAttacking() {
    return attacking;
  }

  public void setAttacking(boolean attacking) {
    if (attacking)
      attackingTime = 0;

    this.attacking = attacking;
  }

  public float getAttackingTime() {
    return attackingTime;
  }

  public void setAttackingTime(float attackingTime) {
    this.attackingTime = attackingTime;
  }
}

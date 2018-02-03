package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.world.World;
import java.util.Optional;

public class AIGladiator extends Gladiator {
  private Vector2 destination;
  private Gladiator enemy;

  public AIGladiator(World world) {
    super(world);
  }

  @Override
  public float getSpeed() {
    return 2F;
  }

  public void setDestination(float x, float y) {
    destination = new Vector2(x, y);
  }

  public Optional<Vector2> getDestination() {
    return Optional.ofNullable(destination);
  }

  public void clearDestination() {
    this.destination = null;
  }

  public Optional<Gladiator> getEnemy() {
    return Optional.ofNullable(enemy);
  }

  public void setEnemy(Gladiator enemy) {
    this.enemy = enemy;
  }
}

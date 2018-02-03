package com.keenant.secutor.engine.model.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.world.World;
import java.util.Optional;

public class AIGladiator extends Gladiator {
  private Vector2 destination;

  public AIGladiator(World world) {
    super(world);
  }

  @Override
  public float getSpeed() {
    return 2F;
  }

  public void setDestination(Vector2 destination) {
    this.destination = destination;
  }

  public Optional<Vector2> getDestination() {
    return Optional.ofNullable(destination);
  }

  public void clearDestination() {
    this.destination = null;
  }
}

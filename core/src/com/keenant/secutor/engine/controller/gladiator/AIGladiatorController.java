package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.utils.Direction;

public class AIGladiatorController extends GladiatorController<AIGladiator> {
  public AIGladiatorController(AIGladiator model) {
    super(model, new GladiatorView(model));
  }

  @Override
  public void update(float deltaTime) {
    Vector2 position = model.getPosition();
    Vector2 center = new Vector2();
    model.getBoundingBox().getCenter(center);

    Vector2 destination = model.getDestination().orElse(null);

    if (destination != null) {
      if (position.dst(destination) < 8) {
        model.setMovement(0, 0);
        model.clearDestination();
      }
      else {

        Vector2 movement = destination.cpy().sub(position).nor().scl(model.getSpeed());
        model.setMovement(movement.x, movement.y);
        model.setFacing(Direction.fromVector(movement));
      }
    }

    super.update(deltaTime);
  }
}

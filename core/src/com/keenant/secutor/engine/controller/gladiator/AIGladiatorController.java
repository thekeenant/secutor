package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import java.util.Random;

public class AIGladiatorController extends GladiatorController<AIGladiator> {
  private static final Random random = new Random();
  private long lastDestTime = 0;

  public AIGladiatorController(AIGladiator model, GladiatorView view) {
    super(model, view);
  }

  @Override
  public void update(float deltaTime) {
    Vector2 position = model.getPosition();
    Vector2 center = new Vector2();
    model.getBoundingBox().getCenter(center);

    Gladiator enemy = model.getEnemy().orElse(null);

    if (enemy != null) {
      model.setDestination(enemy.center());
    }
    else {
      if (System.currentTimeMillis() - lastDestTime > 2000) {
        if (random.nextDouble() < 0.3) {
          model.setDestination(new Vector2(model.getX() + random.nextInt(50) - 25,
              model.getY() + random.nextInt(50) - 25));
        }
        lastDestTime = System.currentTimeMillis();
      }
    }


    Vector2 destination = model.getDestination().orElse(null);

    if (destination != null) {
      if (center.dst2(destination) < 50) {
        model.clearDestination();
      }
      else {
        Vector2 movement = destination.cpy().sub(position);
        movement.nor().scl(8f * (deltaTime / (1F / 2F)));

        if (super.testMovement(movement.x, movement.y)) {
          model.setPosition(model.getX() + movement.x, model.getY() + movement.y);
          model.setLastMovement(movement.x, movement.y);
        }
      }
    }

    super.testMovement(0, 0);


    super.update(deltaTime);
  }
}

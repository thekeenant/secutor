package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.utils.Direction;
import com.keenant.secutor.utils.Utils;
import java.util.Random;

public class AIGladiatorController extends GladiatorController<AIGladiator> {
  private static final Random random = new Random();
  private long lastDestTime = 0;

  public AIGladiatorController(AIGladiator model) {
    super(model, new GladiatorView(model));
  }

  int speedy = Utils.random().nextInt(5000) + 3000;

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
      if (System.currentTimeMillis() - lastDestTime > speedy) {
        model.setDestination(new Vector2(model.getX() + random.nextInt(50) - 25,
            model.getY() + random.nextInt(50) - 25));
        lastDestTime = System.currentTimeMillis();
      }
    }


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

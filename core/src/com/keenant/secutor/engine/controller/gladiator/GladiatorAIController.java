package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import java.util.Random;

public class GladiatorAIController extends AbstractController<Gladiator, GladiatorView> {
  public GladiatorAIController(Gladiator model, GladiatorView view) {
    super(model, view);
  }

  Random random = new Random();

  @Override
  public void update(float deltaTime) {
    Vector2 movement = new Vector2(random.nextInt(3) - 1, random.nextInt(3) - 1);
    movement.nor().scl(8f * (deltaTime / (1 / 4F)));
    model.setPosition(model.getX() + movement.x, model.getY() + movement.y);
  }
}

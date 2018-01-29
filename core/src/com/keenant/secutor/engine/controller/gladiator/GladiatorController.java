package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.controller.head.HeadController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.world.Direction;

public class GladiatorController extends AbstractController<Gladiator, GladiatorView> {
  private final HeadController head;

  public GladiatorController(Gladiator model, GladiatorView view) {
    super(model, view);

    head = new HeadController(model.getHead(), view.getHead());
  }

  @Override
  public void update(float deltaTime) {
    float x = model.getX();
    float y = model.getY();

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      x += 1;
      model.setFacing(Direction.RIGHT);
    }
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      x -= 1;
      model.setFacing(Direction.LEFT);
    }
    if (Gdx.input.isKeyPressed(Keys.UP)) {
      y += 1;
      model.setFacing(Direction.UP);
    }
    if (Gdx.input.isKeyPressed(Keys.DOWN)) {
      y -= 1;
      model.setFacing(Direction.DOWN);
    }

    model.setRunning(model.getX() != x || model.getY() != y);

    head.update(deltaTime);
  }
}

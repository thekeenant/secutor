package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.gladiator.GladiatorPart;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.world.Direction;

public class GladiatorController extends AbstractController<Gladiator, GladiatorView> {
  private final HeadController head;

  private Vector2 target;
  private float speed = 1.0f;
  private float moveCoef = 0;

  public GladiatorController(Gladiator model, GladiatorView view) {
    super(model, view);

    head = new HeadController(model, model.getHead(), view.getHead());
  }

  @Override
  public void update(float deltaTime) {
    GladiatorAnimationState animationState = view.currentAnimationState();

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
    model.setPosition(x, y);

    head.setOffset(animationState.getParts().get(GladiatorPart.HEAD));
    head.update(deltaTime);

    moveCoef += deltaTime;
  }
}

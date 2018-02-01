package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;

public class GladiatorUserController extends AbstractController<Gladiator, GladiatorView> {
  private final Vector2 movement = new Vector2();
  private float speed = 4f;

  public GladiatorUserController(Gladiator model, GladiatorView view) {
    super(model, view);
  }

  @Override
  public void update(float deltaTime) {
    GladiatorAnimationState animationState = view.currentAnimationState();

    movement.x = 0;
    movement.y = 0;

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      movement.x += 1;
    }
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      movement.x -= 1;
    }
    if (Gdx.input.isKeyPressed(Keys.DOWN)) {
      movement.y -= 1;
    }
    if (Gdx.input.isKeyPressed(Keys.UP)) {
      movement.y += 1;
    }

    // normalize movement to ensure max of 1, then scale based on speed
    movement.nor().scl(8f * (deltaTime / (1 / speed)));

    model.setPosition(model.getX() + movement.x, model.getY() + movement.y);
  }
}

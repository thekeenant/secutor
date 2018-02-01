package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;

public class UserGladiatorController extends GladiatorController<Gladiator> {
  private static final Vector2 movement = new Vector2();

  public UserGladiatorController(Gladiator model, GladiatorView view) {
    super(model, view);
  }

  @Override
  public void update(float deltaTime) {
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

    if (!movement.isZero()) {
      // normalize movement to ensure max of 1, then scale based on speed
      movement.nor().scl(8f * (deltaTime / (1F / 4F)));

      if (testMovement(movement.x, movement.y)) {
        model.setPosition(model.getX() + movement.x, model.getY() + movement.y);
        model.setLastMovement(movement.x, movement.y);
      }
    }

    super.update(deltaTime);
  }
}

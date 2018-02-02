package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.utils.Direction;

public class UserGladiatorController extends GladiatorController<Gladiator> {
  private static final Vector2 movement = new Vector2();

  public UserGladiatorController(Gladiator model, GladiatorView view) {
    super(model, view);
  }

  @Override
  public void update(float deltaTime) {
    movement.x = 0;
    movement.y = 0;

    if (Gdx.input.isKeyPressed(Keys.W)) {
      movement.y += 1;
    }
    if (Gdx.input.isKeyPressed(Keys.A)) {
      movement.x -= 1;
    }
    if (Gdx.input.isKeyPressed(Keys.S)) {
      movement.y -= 1;
    }
    if (Gdx.input.isKeyPressed(Keys.D)) {
      movement.x += 1;
    }

    float mod = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) ? 1.5F : 1F;

    movement.nor().scl(model.getSpeed() * mod);
    model.setMovement(movement.x, movement.y);

    if (!movement.isZero()) {
      model.setFacing(Direction.fromVector(movement.cpy()));
    }

    super.update(deltaTime);
  }
}

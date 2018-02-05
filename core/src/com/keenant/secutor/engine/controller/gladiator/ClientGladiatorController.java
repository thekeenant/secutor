package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.ClientGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;

/**
 * Controls the client's gladiator.
 */
public class ClientGladiatorController extends GladiatorController<ClientGladiator> {
  private static final Vector2 movement = new Vector2();

  public ClientGladiatorController(ClientGladiator model) {
    super(model);
  }

  @Override
  public void update(float deltaTime) {
    Gladiator model = getModel();

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

    if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      if (!model.isAttacking())
        model.setAttacking(true);
    }

    float mod = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) ? 2F : 1F;

    movement.nor().scl(model.getSpeed() * mod);
    model.setMovement(movement.x, movement.y);

    super.update(deltaTime);
  }
}

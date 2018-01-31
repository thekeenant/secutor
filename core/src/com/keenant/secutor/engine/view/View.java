package com.keenant.secutor.engine.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The V in MVC. This reads from the model and renders the entity to the screen.
 */
public interface View {
  void render(SpriteBatch batch, float deltaTime);
}

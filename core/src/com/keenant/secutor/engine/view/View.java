package com.keenant.secutor.engine.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.engine.model.Model;

/**
 * The V in MVC. This reads from the model and renders the entity to the screen.
 */
public interface View<M extends Model> {
  M getModel();

  void setModel(M model);

  void draw(SpriteBatch batch, float deltaTime);
}

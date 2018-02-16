package com.keenant.secutor.engine.controller;

import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.model.Model;
import com.keenant.secutor.engine.view.View;

/**
 * The C in MVC. This encapsulates the logic of an entity such as input, interaction, and various
 * calculations. It modifies the model with is later relayed to the view, which can also be read
 * from the controller.
 * @param <M> the model
 * @param <V> the view
 */
public interface Controller<M extends Model, V extends View<M>> {
  M getModel();

  V getView();

  void setModel(M model);

  void setView(V view);

  /**
   * Perform the necessary updates to the model
   * @param game the game context
   * @param deltaTime the time elapsed between updates
   */
  void act(Game game, float deltaTime);
}

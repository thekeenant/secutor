package com.keenant.secutor.engine.controller;

import com.keenant.secutor.engine.model.Model;
import com.keenant.secutor.engine.view.View;

/**
 * The C in MVC. This encapsulates the logic of an entity such as input, interaction, and various
 * calculations. It modifies the model with is later relayed to the view, which can also be read
 * from the controller.
 * @param <M> the model
 * @param <V> the view
 */
public interface Controller<M extends Model, V extends View> {
  M getModel();

  V getView();

  void update(float deltaTime);
}

package com.keenant.secutor.engine.model;

import com.keenant.secutor.engine.controller.Controller;

/**
 * The M in MVC. It contains data relevant to an entity in the game.
 *
 * It is mainly here for the sake of documentation.
 */
public interface Model {
  /**
   * Generate a default model controller using this model.
   * @return the new controller
   */
  Controller<?, ?> createController();
}

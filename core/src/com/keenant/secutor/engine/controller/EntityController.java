package com.keenant.secutor.engine.controller;

import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.view.View;

public abstract class EntityController<M extends Entity, V extends View> extends AbstractController<M, V> {
  public EntityController(M model, V view) {
    super(model, view);
  }
}

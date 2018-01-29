package com.keenant.secutor.engine.controller;

import com.keenant.secutor.engine.model.Model;
import com.keenant.secutor.engine.view.View;

public abstract class AbstractController<M extends Model, V extends View> implements Controller<M, V> {
  protected final M model;
  protected final V view;

  public AbstractController(M model, V view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public M getModel() {
    return model;
  }

  @Override
  public V getView() {
    return view;
  }
}

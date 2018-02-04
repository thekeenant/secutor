package com.keenant.secutor.engine.controller;

import com.keenant.secutor.engine.model.Model;
import com.keenant.secutor.engine.view.View;

public abstract class AbstractController<M extends Model, V extends View<M>> implements Controller<M, V> {
  private M model;
  private V view;

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

  public void setModel(M model) {
    this.model = model;
    this.view.setModel(model);
  }

  public void setView(V view) {
    this.view = view;
    this.view.setModel(model);
  }
}

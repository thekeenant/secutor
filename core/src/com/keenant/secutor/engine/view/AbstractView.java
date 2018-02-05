package com.keenant.secutor.engine.view;

import com.keenant.secutor.engine.model.Model;

public abstract class AbstractView<M extends Model> implements View<M> {
  private M model;

  public AbstractView(M model) {
    this.model = model;
  }

  public M getModel() {
    return model;
  }

  public void setModel(M model) {
    this.model = model;
  }
}

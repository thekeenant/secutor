package com.keenant.secutor.engine.view;

public abstract class AbstractView<M> implements View {
  protected final M model;

  public AbstractView(M model) {
    this.model = model;
  }
}

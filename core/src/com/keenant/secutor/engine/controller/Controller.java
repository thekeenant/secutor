package com.keenant.secutor.engine.controller;

import com.keenant.secutor.engine.model.Model;
import com.keenant.secutor.engine.view.View;

public interface Controller<M extends Model, V extends View> {
  M getModel();

  V getView();

  void update(float deltaTime);
}

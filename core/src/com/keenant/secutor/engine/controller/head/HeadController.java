package com.keenant.secutor.engine.controller.head;

import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.model.head.Head;
import com.keenant.secutor.engine.view.head.HeadView;

public class HeadController extends AbstractController<Head, HeadView> {
  public HeadController(Head model, HeadView view) {
    super(model, view);
  }

  @Override
  public void update(float deltaTime) {

  }
}

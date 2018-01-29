package com.keenant.secutor.engine.controller.head;

import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.head.Head;
import com.keenant.secutor.engine.view.head.HeadView;

public class HeadController extends AbstractController<Head, HeadView> {
  private final Gladiator gladiator;

  public HeadController(Gladiator gladiator, Head model, HeadView view) {
    super(model, view);
    this.gladiator = gladiator;
  }

  @Override
  public void update(float deltaTime) {
    model.setPosition(gladiator.getX(), gladiator.getY());
  }
}

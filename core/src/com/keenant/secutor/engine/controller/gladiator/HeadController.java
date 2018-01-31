package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.gladiator.Head;
import com.keenant.secutor.engine.view.gladiator.HeadView;

public class HeadController extends AbstractController<Head, HeadView> {
  private final Gladiator gladiator;
  private Vector2 offset = new Vector2();

  public HeadController(Gladiator gladiator, Head model, HeadView view) {
    super(model, view);
    this.gladiator = gladiator;
  }

  public void setOffset(Vector2 offset) {
    this.offset = offset;
  }

  @Override
  public void update(float deltaTime) {
    model.setPosition(gladiator.getX() + offset.x, gladiator.getY() + offset.y);
  }
}

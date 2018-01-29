package com.keenant.secutor.engine.view.head;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.engine.model.head.Head;
import com.keenant.secutor.engine.view.AbstractView;

public class HeadView extends AbstractView<Head> {
  private final Texture helmet;

  public HeadView(Head head) {
    super(head);
    this.helmet = new Texture(Gdx.files.internal("helmet.png"));
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    batch.draw(helmet, model.getPosition().x, model.getPosition().y);
  }
}

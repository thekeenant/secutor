package com.keenant.secutor.engine.view.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.engine.controller.Controller;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.AbstractView;

public class WorldView extends AbstractView<World> {
  private Texture texture;
  public WorldView(World model) {
    super(model);
    texture = new Texture(Gdx.files.internal("background.png"));
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    batch.draw(texture, 0, 0);

    for (Controller controller : model.getControllers()) {
      controller.getView().render(batch, deltaTime);
    }
  }
}

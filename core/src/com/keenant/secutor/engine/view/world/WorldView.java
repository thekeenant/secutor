package com.keenant.secutor.engine.view.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.SecutorAssets;
import com.keenant.secutor.engine.controller.Controller;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.AbstractView;

public class WorldView extends AbstractView<World> {
  public WorldView(World model) {
    super(model);
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    batch.draw(SecutorAssets.BACKGROUND, 0, 0);

    // for each child render it to display
    for (Controller controller : model.getControllers()) {
      controller.getView().render(batch, deltaTime);
    }
  }
}

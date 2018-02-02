package com.keenant.secutor.engine.view.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.SecutorAssets;
import com.keenant.secutor.engine.controller.Controller;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.engine.view.View;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorldView extends AbstractView<World> {
  public WorldView(World model) {
    super(model);
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    batch.draw(SecutorAssets.BACKGROUND, 0, 0);

    List<EntityController<?, ?>> controllers = new ArrayList<>(model.getControllers());

    controllers.sort((c1, c2) -> {
      Entity e1 = c1.getModel();
      Entity e2 = c2.getModel();

      return Float.compare(e2.getY(), e1.getY());
    });

    for (EntityController controller : controllers) {
      controller.getView().render(batch, deltaTime);
    }

//    batch.draw(SecutorAssets.WHITE, 0, 0, 16, 16);
  }
}

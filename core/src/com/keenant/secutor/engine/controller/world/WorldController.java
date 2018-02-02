package com.keenant.secutor.engine.controller.world;

import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.controller.Controller;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.world.WorldView;

public class WorldController extends AbstractController<World, WorldView> {
  public WorldController(World model, WorldView view) {
    super(model, view);
  }

  public WorldController(World model) {
    this(model, new WorldView(model));
  }

  @Override
  public void update(float deltaTime) {
    for (Controller controller : model.getEntities()) {
      controller.update(deltaTime);
    }
  }
}

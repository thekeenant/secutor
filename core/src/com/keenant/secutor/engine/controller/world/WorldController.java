package com.keenant.secutor.engine.controller.world;

import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.controller.Controller;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.world.WorldView;

public class WorldController extends AbstractController<World, WorldView> {
  public WorldController(World model, WorldView view) {
    super(model, view);
  }

  public void addController(Controller controller) {
    model.getControllers().add(controller);
  }

  @Override
  public void update(float deltaTime) {
    for (Controller controller : model.getControllers()) {
      controller.update(deltaTime);
    }
  }
}
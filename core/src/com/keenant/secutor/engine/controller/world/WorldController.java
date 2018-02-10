package com.keenant.secutor.engine.controller.world;

import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.world.WorldView;
import java.util.HashMap;
import java.util.Map;

public class WorldController extends AbstractController<World, WorldView> {
  private final Map<Entity, EntityController<?, ?>> controllers = new HashMap<>();

  public WorldController(World model) {
    super(model, new WorldView(model));
  }

  private EntityController<?, ?> getController(Entity entity) {
    EntityController<?, ?> controller = controllers.computeIfAbsent(entity, Entity::createController);
    getView().setView(entity, controller.getView());
    return controller;
  }

  @Override
  public void act(Game game, float deltaTime) {
    World world = getModel();

    while (!world.getEntitiesToRemove().isEmpty()) {
      Entity remove = world.getEntitiesToRemove().remove(0);
      getView().removeEntity(remove);
      controllers.remove(remove);
      world.getEntities().remove(remove);
    }

    for (Entity entity : world.getEntities()) {
      EntityController<?, ?> controller = getController(entity);
      controller.act(game, deltaTime);
    }
  }
}

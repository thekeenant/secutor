package com.keenant.secutor.engine.model.world;

import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class World implements Model {
  private final List<Entity> entities = new ArrayList<>();
  private final List<Entity> entitiesToRemove = new ArrayList<>();

  public List<Entity> getEntities() {
    return entities;
  }

  public Optional<Entity> getEntity(UUID uuid) {
    return entities.stream().filter(entity -> Objects.equals(entity.getUuid(), uuid)).findFirst();
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  @Override
  public WorldController createController() {
    return new WorldController(this);
  }

  public void removeEntity(Entity remove) {
    this.entitiesToRemove.add(remove);
  }

  public List<Entity> getEntitiesToRemove() {
    return entitiesToRemove;
  }
}

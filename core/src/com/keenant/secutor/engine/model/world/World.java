package com.keenant.secutor.engine.model.world;

import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.Model;
import java.util.ArrayList;
import java.util.List;

public class World implements Model {
  private final List<EntityController<?, ?>> entities = new ArrayList<>();


  public List<EntityController<?, ?>> getControllers() {
    return entities;
  }
}

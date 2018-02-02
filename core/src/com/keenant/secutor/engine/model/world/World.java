package com.keenant.secutor.engine.model.world;

import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.controller.gladiator.AIGladiatorController;
import com.keenant.secutor.engine.model.Model;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class World implements Model {
  private final List<EntityController<?, ?>> entities = new ArrayList<>();

  public List<EntityController<?, ?>> getEntities() {
    return entities;
  }

  public void addEntity(EntityController<?, ?> entity) {
    entities.add(entity);
  }

  public void makeInteresting(int scale, Gladiator player) {
    for (int i = 0 ; i < scale; i++) {
      AIGladiator ai = new AIGladiator(this);
      addEntity(new AIGladiatorController(ai));

      if (Utils.random().nextFloat() < 0.5F)
        ai.setEnemy(player);

      ai.setPosition(Utils.random().nextFloat() * scale, Utils.random().nextFloat() * scale);
    }
  }
}

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
    AIGladiator a1 = new AIGladiator(this);
    AIGladiator a2 = new AIGladiator(this);

    a1.setEnemy(a2);
    a2.setEnemy(a1);

    a1.setPosition(Utils.random().nextFloat() * scale + 80, Utils.random().nextFloat() * scale);
    a2.setPosition(Utils.random().nextFloat() * scale, Utils.random().nextFloat() * scale);


    addEntity(new AIGladiatorController(a1));
    addEntity(new AIGladiatorController(a2));
  }
}

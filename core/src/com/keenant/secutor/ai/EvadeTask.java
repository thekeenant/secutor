package com.keenant.secutor.ai;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;

public class EvadeTask extends LeafTask<AIGladiator> {

  @Override
  public Status execute() {
    AIGladiator ai = getObject();
    Gladiator enemy = getObject().getEnemy().orElse(null);

    if (enemy == null)
      return Status.FAILED;

    Vector2 movement = ai.getPosition().cpy().sub(enemy.getPosition()).nor();
    Vector2 dest = ai.getPosition().cpy().add(movement.scl(6));

    ai.setDestination(dest.x, dest.y);
    return Status.RUNNING;
  }

  @Override
  protected Task<AIGladiator> copyTo(Task<AIGladiator> task) {
    return task;
  }
}

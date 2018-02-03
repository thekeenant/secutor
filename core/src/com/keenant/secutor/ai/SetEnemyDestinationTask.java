package com.keenant.secutor.ai;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;

public class SetEnemyDestinationTask extends LeafTask<AIGladiator> {
  @Override
  public Status execute() {
    AIGladiator ai = getObject();
    Gladiator enemy = ai.getEnemy().orElse(null);

    if (enemy == null)
      return Status.FAILED;

    ai.setDestination(enemy.getPosition().x, enemy.getPosition().y);
    return Status.SUCCEEDED;
  }

  @Override
  protected Task<AIGladiator> copyTo(Task<AIGladiator> task) {
    return task;
  }
}

package com.keenant.secutor.ai;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;

public class EnemyInRangeCondition extends LeafTask<AIGladiator> {
  @Override
  public Status execute() {
    AIGladiator ai = getObject();
    Gladiator enemy = getObject().getEnemy().orElse(null);

    if (enemy == null)
      return Status.FAILED;

    return ai.getPosition().dst(enemy.getPosition()) < 64 ? Status.SUCCEEDED : Status.FAILED;
  }

  @Override
  protected Task<AIGladiator> copyTo(Task<AIGladiator> task) {
    return task;
  }
}

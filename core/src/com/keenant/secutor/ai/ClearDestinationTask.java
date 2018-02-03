package com.keenant.secutor.ai;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;

public class ClearDestinationTask extends LeafTask<AIGladiator> {

  @Override
  public Status execute() {
    getObject().clearDestination();
    return Status.SUCCEEDED;
  }

  @Override
  protected Task<AIGladiator> copyTo(Task<AIGladiator> task) {
    return task;
  }
}

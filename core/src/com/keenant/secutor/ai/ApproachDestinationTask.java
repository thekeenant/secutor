package com.keenant.secutor.ai;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import java.util.Objects;

public class ApproachDestinationTask extends LeafTask<AIGladiator> {
  private Vector2 destination;

  @Override
  public void start() {
    destination = getObject().getDestination().orElse(null);
  }

  @Override
  public Status execute() {
    // no destination set
    if (destination == null)
      return Status.FAILED;

    AIGladiator ai = getObject();

    // destination changed?
    if (!Objects.equals(destination, ai.getDestination().orElse(null)))
      return Status.FAILED;

    boolean reached = ai.getPosition().dst(destination) < 6;

    return reached ? Status.SUCCEEDED : Status.RUNNING;
  }

  @Override
  protected Task<AIGladiator> copyTo(Task<AIGladiator> task) {
    return task;
  }
}

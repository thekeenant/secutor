package com.keenant.secutor.ai;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.utils.Utils;
import java.util.Objects;

public class WanderTask extends LeafTask<AIGladiator> {
  private Vector2 destination;

  @Override
  public void start() {
    destination = new Vector2(Utils.random().nextFloat() * 50 - 25, Utils.random().nextFloat() * 50 - 25);
    getObject().setDestination(destination.x, destination.y);

    System.out.println("Wandering...");
  }

  @Override
  public Status execute() {
    AIGladiator gladiator = getObject();

    if (Objects.equals(destination, gladiator.getDestination().orElse(null))) {
      boolean arrived = gladiator.getPosition().dst(destination) < 2;
      return arrived ? Status.SUCCEEDED : Status.RUNNING;
    }
    else {
      System.out.println("FAILED wondering.");
      return Status.FAILED;
    }
  }

  @Override
  public void end() {
    System.out.println("Done wondering.");
  }

  @Override
  protected Task<AIGladiator> copyTo(Task<AIGladiator> task) {
    return task;
  }
}

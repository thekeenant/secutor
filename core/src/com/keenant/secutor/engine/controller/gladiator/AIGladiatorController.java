package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.DynamicGuardSelector;
import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.badlogic.gdx.ai.btree.branch.RandomSelector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.ai.ApproachDestinationTask;
import com.keenant.secutor.ai.AttackTask;
import com.keenant.secutor.ai.EnemyInRangeCondition;
import com.keenant.secutor.ai.EnemyIsAttackingCondition;
import com.keenant.secutor.ai.EvadeTask;
import com.keenant.secutor.ai.SetEnemyDestinationTask;
import com.keenant.secutor.ai.WanderTask;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.utils.Direction;

public class AIGladiatorController extends GladiatorController<AIGladiator> {
  private BehaviorTree<AIGladiator> tree;

  @SuppressWarnings("unchecked")
  public AIGladiatorController(AIGladiator model) {
    super(model, new GladiatorView(model));

    Task<AIGladiator> run = new EvadeTask();
    run.setGuard(new EnemyIsAttackingCondition());

    Task<AIGladiator> fight = new DynamicGuardSelector<>(
        run,
        new AttackTask()
    );

    Task<AIGladiator> hostile = new Parallel<>(
        new SetEnemyDestinationTask(),
        new Sequence<>(
            new ApproachDestinationTask(),
            fight
        )
    );
    hostile.setGuard(new EnemyInRangeCondition());

    Task<AIGladiator> wanderAndWait = new RandomSelector<>(
        new Sequence<>(
            new WanderTask(),
            new Wait<>(1)
        ),
        new Wait<>(5)
    );

    tree = new BehaviorTree<>(
        new DynamicGuardSelector<>(
            hostile,
            wanderAndWait
        )
    );
    tree.setObject(model);
  }

  @Override
  public void update(float deltaTime) {
    tree.step();

    Vector2 position = model.getPosition();
    Vector2 center = new Vector2();
    model.getBoundingBox().getCenter(center);

    Vector2 destination = model.getDestination().orElse(null);

    if (destination == null) {
      model.setMovement(0, 0);
    }
    else {
      if (position.dst(destination) < 2) {
        model.setMovement(0, 0);
      }
      else {
        Vector2 movement = destination.cpy().sub(position).nor().scl(model.getSpeed());
        model.setMovement(movement.x, movement.y);
        model.setFacing(Direction.fromVector(movement));
      }
    }

    super.update(deltaTime);
  }
}

package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.Constants;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.event.EntityMoveEvent;
import com.keenant.secutor.utils.Direction;

public class GladiatorController<M extends Gladiator> extends EntityController<M, GladiatorView<M>> {
  public GladiatorController(M model, GladiatorView<M> view) {
    super(model, view);
  }

  public GladiatorController(M model) {
    this(model, new GladiatorView<>(model));
  }

  @Override
  public void act(Game game, float deltaTime) {
    Gladiator model = getModel();
    GladiatorView<M> view = getView();

    Vector2 pos = model.getPosition();
    Vector2 velocity = model.getVelocity();
    Vector2 movement = model.getMovement();

    if (!movement.isZero())
      model.setFacing(Direction.fromVector(movement.cpy()));

    Vector2 change = velocity.cpy();
    // only allow movement if they are stronger than the force being applied to them
    if (movement.len2() > velocity.len2()) {
      change.add(movement);
    }
    change.scl(Constants.METER * deltaTime);

    // next position is current pos plus change
    Vector2 nextPos = pos.cpy().add(change);

    // dampen velocity
    Vector2 deceleration = velocity.cpy().nor().scl(deltaTime * Constants.DECELERATION);
    if (deceleration.len2() >= velocity.len2()) {
      // decelerate to zero if deceleration is greater than curr velocity
      velocity.setZero();
    }
    else {
      // decelerate by calculated amount
      velocity.sub(deceleration);
    }


    // TODO: Perform collision handling elsewhere...?

    GladiatorAnimationState animationState = view.currentAnimationState();
    Rectangle feet = animationState.getFeetBox();

    if (!model.getPosition().equals(nextPos)) {
      game.post(new EntityMoveEvent<>(model, model.getPosition(), nextPos));
      model.setPosition(nextPos.x, nextPos.y);

      Rectangle box = model.getBoundingBox();
      box.set(
          nextPos.x + feet.x,
          nextPos.y + feet.y,
          feet.width,
          feet.height
      );
    }
  }
}

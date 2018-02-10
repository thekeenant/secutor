package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.Constants;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.utils.Direction;
import com.keenant.secutor.utils.GameAnimation;

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
    Vector2 nextPos = model.getPosition().cpy().add(change);

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

    Rectangle boundingBox = new Rectangle(
        nextPos.x + feet.x,
        nextPos.y + feet.y,
        feet.width,
        feet.height
    );

    Rectangle boundingBoxX = new Rectangle(
        nextPos.x + feet.x,
        feet.y,
        feet.width,
        feet.height
    );

    Rectangle boundingBoxY = new Rectangle(
        feet.x,
        nextPos.y + feet.y,
        feet.width,
        feet.height
    );

    boolean allowedXY = true;
    boolean allowedX = true;
    boolean allowedY = true;

//    for (Entity entity : getModel().getWorld().getEntities()) {
//      if (entity.equals(model))
//        continue;
//
//      if (entity instanceof Gladiator) {
//        Gladiator other = (Gladiator) entity;
//
//        if (other.isColliding(boundingBox)) {
//          allowedXY = false;
//        }
//
//        if (other.isColliding(boundingBoxX)) {
//          allowedX = false;
//        }
//
//        if (other.isColliding(boundingBoxY)) {
//          allowedY = false;
//        }
//
//        if (other.isColliding(boundingBox, 1F)) {
//          float randomness = 0.0F;
//          float randomX = (Utils.random().nextFloat() - 0.5f) * randomness;
//          float randomY = (Utils.random().nextFloat() - 0.5f) * randomness;
//
//          Vector2 push = other.getPosition().cpy().sub(nextPos).nor().scl(1.5F).add(randomX, randomY);
//          other.setVelocity(push.x, push.y);
//        }
//      }
//    }
//
//    if (model instanceof AIGladiator && !allowedXY) {
//      if (allowedX) {
//        nextPos.y = model.getY();
//        boundingBox = boundingBoxX;
//      }
//      else if (allowedY) {
//        nextPos.x = model.getX();
//        boundingBox = boundingBoxY;
//      }
//    }

    if (allowedXY || allowedX || allowedY) {
      model.setPosition(nextPos.x, nextPos.y);
      model.setBoundingBox(boundingBox);
    }
  }
}

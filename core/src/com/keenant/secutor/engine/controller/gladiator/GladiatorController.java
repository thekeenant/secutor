package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.Constants;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.CollidableEntity;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;

public class GladiatorController<M extends Gladiator> extends EntityController<M, GladiatorView> {
  public GladiatorController(M model, GladiatorView view) {
    super(model, view);
  }

  @Override
  public void update(float deltaTime) {
    Vector2 velocity = model.getVelocity();
    Vector2 movement = model.getMovement();

    Vector2 change = velocity.cpy().add(movement).scl(Constants.METER * deltaTime);

    model.setPosition(model.getX() + change.x, model.getY() + change.y);
    velocity.sub(velocity.cpy().nor().scl(deltaTime * 2));

    GladiatorAnimationState animationState = view.currentAnimationState();
    Rectangle feet = animationState.getFeetBox();
    Vector2 pos = model.getPosition();

    Rectangle boundingBox = new Rectangle(
        pos.x + feet.x,
        pos.y + feet.y,
        feet.width,
        feet.height
    );

    model.setBoundingBox(boundingBox);

    for (EntityController<?, ?> controller : getModel().getWorld().getControllers()) {
      if (controller.equals(this))
        continue;

      if (controller.getModel() instanceof Gladiator) {
        Gladiator other = (Gladiator) controller.getModel();

        if (other.isColliding(model.getBoundingBox(), 1F)) {
          Vector2 push = other.getPosition().cpy().sub(pos).nor().scl(2F);
          other.setVelocity(push.x, push.y);

          other.setHealth(Math.max(0, other.getHealth() - 1F * deltaTime));
        }
      }
    }
  }
}

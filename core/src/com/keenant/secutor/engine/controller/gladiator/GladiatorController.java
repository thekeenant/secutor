package com.keenant.secutor.engine.controller.gladiator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.controller.EntityController;
import com.keenant.secutor.engine.model.CollidableEntity;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import java.util.Random;

public class GladiatorController<M extends Gladiator> extends EntityController<M, GladiatorView> {
  public GladiatorController(M model, GladiatorView view) {
    super(model, view);
  }

  protected boolean testMovement(float x, float y) {
    GladiatorAnimationState animationState = view.currentAnimationState();
    Rectangle feet = animationState.getFeetBox();

    Rectangle boundingBox = new Rectangle(
        model.getX() + x + feet.x,
        model.getY() + y + feet.y,
        feet.width,
        feet.height
    );

    Rectangle world = new Rectangle(0, 0, 320, 180);

    if (!world.contains(boundingBox)) {
      return false;
    }

    for (EntityController<?, ?> controller : model.getWorld().getControllers()) {
      Entity entity = controller.getModel();

      if (entity.equals(model))
        continue;

      if (entity instanceof CollidableEntity) {
        CollidableEntity collidable = (CollidableEntity) entity;

        if (collidable.isColliding(boundingBox, 2)) {
          if (collidable instanceof Gladiator) {
            Gladiator other = (Gladiator) collidable;

            Vector2 force = other.getPosition().cpy().sub(model.getX() + x, model.getY() + y);
            force.nor().scl(0.05F);

            if (force.epsilonEquals(0, 0)) {
              force.set(new Random().nextFloat() - 0.5F, new Random().nextFloat() - 0.5F);
              force.scl(0.05F);
            }

            other.setPosition(other.getX() + force.x, other.getY() + force.y);


          }
        }
      }
    }

    return true;
  }

  @Override
  public void update(float deltaTime) {
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
  }
}

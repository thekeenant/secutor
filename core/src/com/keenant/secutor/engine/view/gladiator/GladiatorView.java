package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.Assets;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.utils.Direction;
import com.keenant.secutor.utils.GameAnimation;

public class GladiatorView extends AbstractView<Gladiator> {
  private float stateTime;

  public GladiatorView(Gladiator gladiator) {
    super(gladiator);
    stateTime = (float) Math.random();
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    GameAnimation<GladiatorAnimationState> animation = currentAnimation();
    GladiatorAnimationState state = animation.getState(stateTime);

    // draw while taking into account bounding box
    Vector2 origin = state.getOrigin();
    float drawX = model.getX() - origin.x;
    float drawY = model.getY() - origin.y;

    batch.draw(Assets.GLADIATOR_SHADOW.getKeyFrame(stateTime), drawX, model.getY() - 1);
    batch.draw(animation.getKeyFrame(stateTime), drawX, drawY);

    stateTime += deltaTime;
    if (model.isAttacking()) {
      model.setAttackingTime(model.getAttackingTime() + deltaTime);
    }
  }

  public GameAnimation<GladiatorAnimationState> currentAnimation() {
    Direction facing = model.getFacing();
    switch (facing) {
      case UP:
        if (model.isAttacking())
          return Assets.GLADIATOR_UP_ATTACK;
        else
          return Assets.GLADIATOR_UP;
      case DOWN:
        if (model.isAttacking())
          return Assets.GLADIATOR_DOWN_ATTACK;
        else
          return Assets.GLADIATOR_DOWN;
      case LEFT:
        if (model.isAttacking())
          return Assets.GLADIATOR_LEFT_ATTACK;
        else
          return Assets.GLADIATOR_LEFT;
      case RIGHT:
        if (model.isAttacking())
          return Assets.GLADIATOR_RIGHT_ATTACK;
        else
          return Assets.GLADIATOR_RIGHT;
      default:
        return Assets.GLADIATOR_DOWN;
    }
  }

  public GladiatorAnimationState currentAnimationState() {
    float stateTime = model.isAttacking() ? model.getAttackingTime() : this.stateTime;
    return currentAnimation().getState(stateTime);
  }
}

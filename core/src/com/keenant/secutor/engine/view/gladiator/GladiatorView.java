package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.SecutorAssets;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.utils.GameAnimation;
import com.keenant.secutor.world.Direction;

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

    batch.draw(SecutorAssets.GLADIATOR_SHADOW.getKeyFrame(stateTime), drawX, model.getY() - 1);
    batch.draw(animation.getKeyFrame(stateTime), drawX, drawY);


    if (!(model instanceof AIGladiator)) {
      Sprite health = new Sprite(SecutorAssets.WHITE);
      health.setColor(Color.RED);
      health
          .setPosition(model.getX() - 1, model.getY() + origin.y + state.getBox().getHeight() + 1);
      health.setSize(6, 1);
      health.draw(batch);

      health = new Sprite(SecutorAssets.WHITE);
      health.setColor(Color.GREEN);
      health
          .setPosition(model.getX() - 1, model.getY() + origin.y + state.getBox().getHeight() + 1);
      health.setSize((model.getHealth() / model.getMaxHealth()) * 6F, 1);
      health.draw(batch);
    }

    stateTime += deltaTime;
  }

  public GameAnimation<GladiatorAnimationState> currentAnimation() {
    Direction facing = model.getFacing();
    switch (facing) {
      case UP:
        return SecutorAssets.GLADIATOR_UP;
      case DOWN:
        return SecutorAssets.GLADIATOR_DOWN;
      case LEFT:
        return SecutorAssets.GLADIATOR_LEFT;
      case RIGHT:
        return SecutorAssets.GLADIATOR_RIGHT;
      default:
        return SecutorAssets.GLADIATOR_DOWN;
    }
  }

  public GladiatorAnimationState currentAnimationState() {
    return currentAnimation().getState(stateTime);
  }

  public float getStateTime() {
    return stateTime;
  }
}

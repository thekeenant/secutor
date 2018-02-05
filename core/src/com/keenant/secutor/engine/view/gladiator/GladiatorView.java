package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.Assets;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.utils.Direction;
import com.keenant.secutor.utils.GameAnimation;

public class GladiatorView<M extends Gladiator> extends AbstractView<M> {
  private float stateTime;

  public GladiatorView(M gladiator) {
    super(gladiator);
    stateTime = (float) Math.random();
  }

  private void renderHealth(SpriteBatch batch) {
    Gladiator model = getModel();

    Sprite green = new Sprite(Assets.WHITE);
    green.setColor(Color.GREEN);
    green.setAlpha(0.5F);

    Sprite red = new Sprite(Assets.WHITE);
    red.setColor(Color.RED);
    red.setAlpha(0.5F);

    float drawX = model.getX();
    float drawY = model.getY() + 18;
    float width = 4;
    float health = model.getHealth() / model.getMaxHealth();

    green.setPosition(drawX, drawY);
    green.setScale(width * health, 1);
    green.setOrigin(0, 0);
    red.setPosition(drawX + width * health, drawY);
    red.setScale(width * (1 - health), 1);
    red.setOrigin(0, 0);

    green.draw(batch);
    red.draw(batch);
  }

  private void renderName(SpriteBatch batch) {
    Gladiator model = getModel();

    BitmapFont font = Assets.FONT_24;
    font.setUseIntegerPositions(false);
    font.getData().setScale(0.2F);

    // TODO: draw font on different layer (w/ RenderContext object for help)
    font.draw(batch, model.getName(), model.getX(), model.getY());
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    Gladiator model = getModel();

    GameAnimation<GladiatorAnimationState> animation = currentAnimation();
    GladiatorAnimationState state = animation.getState(stateTime);

    // draw while taking into account bounding box
    Vector2 origin = state.getOrigin();
    float drawX = model.getX() - origin.x;
    float drawY = model.getY() - origin.y;

    TextureRegion frame = Assets.GLADIATOR_SHADOW.getKeyFrame(stateTime);

    batch.draw(frame, drawX, model.getY() - 1);
    batch.draw(animation.getKeyFrame(stateTime), drawX, drawY);

    renderHealth(batch);
    renderName(batch);

    stateTime += deltaTime;
    if (model.isAttacking()) {
      model.setAttackingTime(model.getAttackingTime() + deltaTime);
    }
  }

  public GameAnimation<GladiatorAnimationState> currentAnimation() {
    Gladiator model = getModel();

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
    Gladiator model = getModel();

    float stateTime = model.isAttacking() ? model.getAttackingTime() : this.stateTime;
    return currentAnimation().getState(stateTime);
  }
}

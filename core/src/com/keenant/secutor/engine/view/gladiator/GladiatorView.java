package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.keenant.secutor.animation.GladiatorAnimationLogic;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.tools.GameAnimation;

public class GladiatorView extends AbstractView<Gladiator> {
  private HeadView head;
  private float stateTime;

  private final GameAnimation<GladiatorAnimationState> animation;

  public GladiatorView(Gladiator gladiator) {
    super(gladiator);
    head = new HeadView(gladiator, model.getHead());

    TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator/gladiator_down.png")));
    animation = GameAnimation.split(0.5f, texture, 2, PlayMode.LOOP, GladiatorAnimationLogic.DOWN);
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    batch.draw(animation.getKeyFrame(stateTime), model.getX(), model.getY());
    stateTime += deltaTime;
  }

  public GladiatorAnimationState currentAnimationState() {
    return animation.getState(stateTime);
  }

  public HeadView getHead() {
    return head;
  }
}

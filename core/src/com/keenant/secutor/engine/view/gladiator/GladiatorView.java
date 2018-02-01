package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.keenant.secutor.SecutorAssets;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;

public class GladiatorView extends AbstractView<Gladiator> {
  private float stateTime;

  public GladiatorView(Gladiator gladiator) {
    super(gladiator);
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    GladiatorAnimationState state = currentAnimationState();

    // draw while taking into account bounding box
    Rectangle box = state.getBox();
    float drawX = model.getX() - box.getX();
    float drawY = model.getY() - box.getY();

    BitmapFont font = SecutorAssets.FONT_24;
    font.setUseIntegerPositions(false);


    batch.draw(SecutorAssets.GLADIATOR_DOWN.getKeyFrame(stateTime), drawX, drawY);
    font.draw(batch, model.getX() + ", " + model.getY(), model.getX(), model.getY());

    stateTime += deltaTime;
  }

  public GladiatorAnimationState currentAnimationState() {
    return SecutorAssets.GLADIATOR_DOWN.getState(stateTime);
  }
}

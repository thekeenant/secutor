package com.keenant.secutor.tools;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureAnimation extends Animation<TextureRegion> {
  public TextureAnimation(float frameDuration, TextureRegion... keyFrames) {
    super(frameDuration, keyFrames);
  }
}

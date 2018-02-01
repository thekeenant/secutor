package com.keenant.secutor.utils;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.keenant.secutor.utils.GameAnimation.AnimationLogic;

public class GameAnimationParams extends AssetLoaderParameters<GameAnimation> {
  private final float frameDuration;
  private final int keyFrames;
  private final PlayMode playMode;
  private final AnimationLogic logic;
  private final boolean flipX;
  private final boolean flipY;

  public GameAnimationParams(float frameDuration,
      int keyFrames,
      PlayMode playMode,
      AnimationLogic logic,
      boolean flipX,
      boolean flipY) {

    this.frameDuration = frameDuration;
    this.keyFrames = keyFrames;
    this.playMode = playMode;
    this.logic = logic;
    this.flipX = flipX;
    this.flipY = flipY;
  }

  public GameAnimationParams(float frameDuration,
      int keyFrames,
      PlayMode playMode,
      AnimationLogic logic) {
    this(frameDuration, keyFrames, playMode, logic, false, false);
  }

  public float getFrameDuration() {
    return frameDuration;
  }

  public int getKeyFrames() {
    return keyFrames;
  }

  public PlayMode getPlayMode() {
    return playMode;
  }

  public AnimationLogic getLogic() {
    return logic;
  }

  public boolean getFlipX() {
    return flipX;
  }

  public boolean getFlipY() {
    return flipY;
  }
}

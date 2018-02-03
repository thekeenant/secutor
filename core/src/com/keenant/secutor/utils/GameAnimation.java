package com.keenant.secutor.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.keenant.secutor.animation.GladiatorAnimationState;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A type of animation that can keep track of certain game logic. Similar to how {@link Animation}
 * uses {@link Animation#frameDuration} and a stateTime to determine which animation to use,
 * this uses it to determine the positions of features in the animation (i.e. on frame 1, the hands
 * are at 0,5 and at frame 2 they are at 0,6).
 * @param <T> the data object which encapsulates the animation logic
 */
public class GameAnimation<T> extends TextureAnimation {
  private AnimationLogic<T> logic;
  private final Map<Integer, T> results;

  @Override
  public String toString() {
    return getKeyFrames().length + " keyframes";
  }

  public GameAnimation(TextureRegion[] keyFrames,
      float frameDuration,
      PlayMode playMode,
      AnimationLogic<T> logic) {
    super(frameDuration, keyFrames);
    setPlayMode(playMode);
    this.logic = logic;
    results = new HashMap<>();
  }

  public GameAnimation<T> cpy() {
    return new GameAnimation<>(
        Arrays.copyOf(getKeyFrames(), getKeyFrames().length),
        getFrameDuration(),
        getPlayMode(),
        logic
    );
  }

  public GameAnimation<T> flip(boolean flipX, boolean flipY) {
    for (int i = 0; i < getKeyFrames().length; i++) {
      TextureRegion copy = new TextureRegion(getKeyFrames()[i]);
      copy.flip(flipX, flipY);
      getKeyFrames()[i] = copy;
    }
    return this;
  }

  public GameAnimation<T> logic(AnimationLogic<T> logic) {
    this.logic = logic;
    results.clear();
    return this;
  }

  public static <T> GameAnimation<T> split(
      TextureRegion sheet,
      float frameDuration,
      int keyFrames,
      PlayMode playMode,
      AnimationLogic<T> logic,
      boolean flipX,
      boolean flipY) {

    TextureRegion[] textures = sheet.split(sheet.getRegionWidth() / keyFrames,
        sheet.getRegionHeight())[0];

    for (TextureRegion texture : textures)
      texture.flip(flipX, flipY);

    return new GameAnimation<>(textures, frameDuration, playMode, logic);
  }

  public static <T> GameAnimation<T> split(TextureRegion sheet,
      float frameDuration,
      int keyFrames,
      PlayMode playMode,
      AnimationLogic<T> logic) {
    return split(sheet, frameDuration, keyFrames, playMode, logic, false, false);
  }

  public T getState(float stateTime) {
    int index = getKeyFrameIndex(stateTime);
    return getState(index);
  }

  public T getState(int keyFrameIndex) {
    if (results.containsKey(keyFrameIndex))
      return results.get(keyFrameIndex);

    T result = logic.apply(keyFrameIndex);
    results.put(keyFrameIndex, result);
    return result;
  }

  /**
   * A function which takes a keyframe index and outputs game logic corresponding to that index.
   * Function should actually be a function (no randomness) and be onto/surjective.
   *
   * @param <T> the return type
   */
  @FunctionalInterface
  public interface AnimationLogic<T> {
    T apply(int index);
  }
}

package com.keenant.secutor.tools;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;
import java.util.Map;

/**
 * A type of animation that can keep track of certain game logic. Similar to how {@link Animation}
 * uses {@link Animation#frameDuration} and a stateTime to determine which animation to use,
 * this uses it to determine the positions of features in the animation (i.e. on frame 1, the hands
 * are at 0,5 and at frame 2 they are at 0,6).
 */
public class GameAnimation<T> extends TextureAnimation {
  private final LogicFunction<T> logic;
  private final Map<Integer, T> results;

  public GameAnimation(float frameDuration,
      TextureRegion[] keyFrames,
      PlayMode playMode,
      LogicFunction<T> logic) {
    super(frameDuration, keyFrames);
    setPlayMode(playMode);
    this.logic = logic;
    results = new HashMap<>();
  }

  public static <T> GameAnimation<T> split(float frameDuration,
      TextureRegion sheet,
      int keyFrames,
      PlayMode playMode,
      LogicFunction<T> logic,
      boolean flipX,
      boolean flipY) {

    TextureRegion[] textures = sheet.split(sheet.getRegionWidth() / keyFrames,
        sheet.getRegionHeight())[0];

    for (TextureRegion texture : textures)
      texture.flip(flipX, flipY);

    return new GameAnimation<>(frameDuration,
        textures,
        playMode,
        logic);
  }

  public static <T> GameAnimation<T> split(float frameDuration,
      TextureRegion sheet,
      int keyFrames,
      PlayMode playMode,
      LogicFunction<T> logic) {
    return split(frameDuration, sheet, keyFrames, playMode, logic, false, false);
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
  public interface LogicFunction<T> {
    T apply(int index);
  }
}

package com.keenant.secutor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.keenant.secutor.animation.GladiatorAnimationLogic;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.utils.GameAnimation;
import com.keenant.secutor.utils.GameAnimationLoader;
import com.keenant.secutor.utils.GameAnimationParams;

/**
 * Asset manager wrapper.
 */
public class Assets {
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_DOWN;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_DOWN_ATTACK;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_UP;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_UP_ATTACK;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_RIGHT;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_RIGHT_ATTACK;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_LEFT;
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_LEFT_ATTACK;
  public static GameAnimation<Boolean> GLADIATOR_SHADOW;
  public static Texture WHITE;
  public static Texture BACKGROUND;

  public static BitmapFont FONT_24;

  public static Music AUDIO_MENU;

  private static AssetManager manager;

  @SuppressWarnings("unchecked")
  public static void load() {
    manager = new AssetManager();
    manager.setLoader(GameAnimation.class, new GameAnimationLoader(manager.getFileHandleResolver()));

    manager.load("gladiator/gladiator_down.png", GameAnimation.class, new GameAnimationParams(
        0.5F,
        2,
        PlayMode.LOOP,
        GladiatorAnimationLogic.DOWN
    ));
    manager.load("gladiator/gladiator_down_attack.png", GameAnimation.class, new GameAnimationParams(
        0.1F,
        5,
        PlayMode.LOOP,
        GladiatorAnimationLogic.DOWN_ATTACK
    ));
    manager.load("gladiator/gladiator_up.png", GameAnimation.class, new GameAnimationParams(
        0.5F,
        2,
        PlayMode.LOOP,
        GladiatorAnimationLogic.UP
    ));
    manager.load("gladiator/gladiator_up_attack.png", GameAnimation.class, new GameAnimationParams(
        0.1F,
        5,
        PlayMode.LOOP,
        GladiatorAnimationLogic.UP_ATTACK
    ));
    manager.load("gladiator/gladiator_right.png", GameAnimation.class, new GameAnimationParams(
        0.5F,
        2,
        PlayMode.LOOP,
        GladiatorAnimationLogic.RIGHT
    ));
    manager.load("gladiator/gladiator_right_attack.png", GameAnimation.class, new GameAnimationParams(
        0.1F,
        5,
        PlayMode.LOOP,
        GladiatorAnimationLogic.RIGHT_ATTACK
    ));
    manager.load("gladiator/gladiator_shadow.png", GameAnimation.class, new GameAnimationParams(
        0.5F,
        2,
        PlayMode.LOOP,
        (index -> true)
    ));
    manager.load("background.png", Texture.class);
    manager.load("white.png", Texture.class);
    manager.load("audio/menu.mp3", Music.class);

    manager.finishLoading();

    GLADIATOR_DOWN = manager.get("gladiator/gladiator_down.png");
    GLADIATOR_DOWN_ATTACK = manager.get("gladiator/gladiator_down_attack.png");
    GLADIATOR_UP = manager.get("gladiator/gladiator_up.png");
    GLADIATOR_UP_ATTACK = manager.get("gladiator/gladiator_up_attack.png");
    GLADIATOR_RIGHT = manager.get("gladiator/gladiator_right.png");
    GLADIATOR_RIGHT_ATTACK = manager.get("gladiator/gladiator_right_attack.png");
    // TODO: This isn't really good, it is not async
    GLADIATOR_LEFT = GLADIATOR_RIGHT.cpy().flip(true, false).logic(GladiatorAnimationLogic.LEFT);
    GLADIATOR_LEFT_ATTACK = GLADIATOR_RIGHT_ATTACK.cpy().flip(true, false).logic(GladiatorAnimationLogic.LEFT);
    GLADIATOR_SHADOW = manager.get("gladiator/gladiator_shadow.png");
    BACKGROUND = manager.get("background.png");
    WHITE = manager.get("white.png");
    AUDIO_MENU = manager.get("audio/menu.mp3");


    // TODO: through manager
    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("dpcomic.ttf"));
    FreeTypeFontParameter param = new FreeTypeFontParameter();
    param.size = 24;
    FONT_24 = gen.generateFont(param);
  }

  public static void dispose() {
    manager.dispose();
  }

  public static float getProgress() {
    return manager.getProgress();
  }
}

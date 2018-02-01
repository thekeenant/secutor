package com.keenant.secutor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.keenant.secutor.animation.GladiatorAnimationLogic;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.utils.GameAnimation;
import com.keenant.secutor.utils.GameAnimationLoader;
import com.keenant.secutor.utils.GameAnimationParams;

/**
 * Asset manager wrapper.
 */
public class SecutorAssets {
  public static GameAnimation<GladiatorAnimationState> GLADIATOR_DOWN;
  public static Texture BACKGROUND;

  public static BitmapFont FONT_24;

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
    manager.load("background.png", Texture.class);

    manager.finishLoading();

    GLADIATOR_DOWN = manager.get("gladiator/gladiator_down.png");
    BACKGROUND = manager.get("background.png");


    // TODO: through manager
    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("manaspace.regular.ttf"));
    FreeTypeFontParameter param = new FreeTypeFontParameter();
    param.size = 24;
    FONT_24 = gen.generateFont(param);
  }

  public static float getProgress() {
    return manager.getProgress();
  }
}

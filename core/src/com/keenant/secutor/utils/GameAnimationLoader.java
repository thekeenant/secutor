package com.keenant.secutor.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GameAnimationLoader extends
    AsynchronousAssetLoader<GameAnimation, GameAnimationParams> {

  // delegate to texture loader, because we load a texture
  private TextureLoader textureLoader;

  public GameAnimationLoader(FileHandleResolver resolver) {
    super(resolver);
    textureLoader = new TextureLoader(resolver);
  }

  @Override
  public void loadAsync(AssetManager manager, String fileName, FileHandle file,
      GameAnimationParams parameter) {
    textureLoader.loadAsync(manager, fileName, file, null);
  }

  @SuppressWarnings("unchecked")
  @Override
  public GameAnimation loadSync(AssetManager manager, String fileName, FileHandle file,
      GameAnimationParams parameter) {
    // grab texture from texture loader
    Texture texture = textureLoader.loadSync(manager, fileName, file, null);
    TextureRegion region = new TextureRegion(texture);

    // split into an animation
    return GameAnimation.split(
        region,
        parameter.getFrameDuration(),
        parameter.getKeyFrames(),
        parameter.getPlayMode(),
        parameter.getLogic(),
        parameter.getFlipX(),
        parameter.getFlipY()
    );
  }

  @Override
  public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
      GameAnimationParams parameter) {
    return null;
  }
}

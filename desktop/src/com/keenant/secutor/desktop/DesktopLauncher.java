package com.keenant.secutor.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.keenant.secutor.SecutorGame;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = 1920;
    config.height = 1080;
    config.vSyncEnabled = true;
    config.backgroundFPS = -1;

    new LwjglApplication(new SecutorGame(), config);

    Gdx.input.setInputProcessor(new InputAdapter() {
      @Override
      public boolean keyDown(int keycode) {
        System.out.println(keycode);
        config.fullscreen = !config.fullscreen;
        return true;
      }
    });
  }
}


package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.keenant.secutor.SecutorGame;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = 1280;
    config.height = 720;
    config.backgroundFPS = 0;
    new LwjglApplication(new SecutorGame(), config);
  }
}


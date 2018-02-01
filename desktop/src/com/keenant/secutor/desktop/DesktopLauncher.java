package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.keenant.secutor.SecutorGame;

public class DesktopLauncher {
  public static void main (String[] arg) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Secutor");
    config.setWindowedMode(1280, 720);
    config.useVsync(true);
    new Lwjgl3Application(new SecutorGame(), config);
  }
}


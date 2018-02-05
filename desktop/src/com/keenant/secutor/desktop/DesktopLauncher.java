package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.keenant.secutor.SecutorApp;
import com.keenant.secutor.SecutorMode;

public class DesktopLauncher {
  public static void main (String[] args) {
    SecutorMode mode = SecutorMode.valueOf(args.length > 0 ? args[0] : "SOLO");

    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Secutor");
    config.setInitialVisible(mode != SecutorMode.SERVER);
    config.setWindowedMode(1280, 720);
    config.useVsync(true);

    new Lwjgl3Application(new SecutorApp(mode), config);
  }
}


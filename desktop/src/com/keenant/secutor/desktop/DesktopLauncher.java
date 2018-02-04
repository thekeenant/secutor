package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.keenant.secutor.SecutorApp;

public class DesktopLauncher {
  public static void main (String[] args) {
    boolean host = args.length == 1 && args[0].equals("--host");

    System.out.println(host);

    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Secutor");
    config.setInitialVisible(!host);
    config.setWindowedMode(1280, 720);
    config.useVsync(true);

    new Lwjgl3Application(new SecutorApp(host), config);
  }
}


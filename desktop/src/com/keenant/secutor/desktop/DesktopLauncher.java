package com.keenant.secutor.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.keenant.secutor.SecutorApp;
import com.keenant.secutor.SecutorConfig;

public class DesktopLauncher {
  @Parameter(names = {"--help", "-help"}, help = true, description = "Display command help")
  private boolean help;

  public static void main(String[] args) {
    SecutorConfig config = new SecutorConfig();
    DesktopLauncher launcher = new DesktopLauncher();
    JCommander commander = JCommander.newBuilder()
        .addObject(launcher)
        .addObject(config)
        .build();
    try {
      commander.parse(args);
    } catch (ParameterException e) {
      e.usage();
      return;
    }
    launcher.run(commander, config);
  }

  private void run(JCommander commander, SecutorConfig config) {
    if (help) {
      commander.usage();
      return;
    }

    Lwjgl3ApplicationConfiguration lwjglConfig = new Lwjgl3ApplicationConfiguration();
    lwjglConfig.setTitle("Secutor");
    lwjglConfig.setInitialVisible(!config.isHeadless());
    lwjglConfig.setWindowedMode(1280, 720);
    lwjglConfig.useVsync(config.isVsync());

    new Lwjgl3Application(new SecutorApp(config), lwjglConfig);
  }
}


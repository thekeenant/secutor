package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.keenant.secutor.SecutorApp;

public class DesktopLauncher {
  @Parameter(names = {"--host", "-h", "-host"})
  private String host = "localhost";

  @Parameter(names = {"--port", "-p", "-port"})
  private int port = 24602;

  @Parameter(names = {"--server", "-server"})
  private boolean server;

  @Parameter(names = {"--headless", "-headless"})
  private boolean headless;

  @Parameter(names = {"--no-vsync", "-no-vsync"})
  private boolean vsync = true;

  @Parameter(names = {"--help", "-help"}, help = true)
  private boolean help;

  public static void main(String[] args) {
    DesktopLauncher launcher = new DesktopLauncher();
    JCommander commander = JCommander.newBuilder()
        .addObject(launcher)
        .build();
    try {
      commander.parse(args);
    } catch (ParameterException e) {
      e.usage();
      return;
    }
    launcher.run(commander);
  }

  private void run(JCommander commander) {
    if (help) {
      commander.usage();
      return;
    }

    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Secutor");
    config.setInitialVisible(!headless);
    config.setWindowedMode(1280, 720);
    config.useVsync(vsync);

    new Lwjgl3Application(new SecutorApp(host, port, server, headless), config);
  }
}


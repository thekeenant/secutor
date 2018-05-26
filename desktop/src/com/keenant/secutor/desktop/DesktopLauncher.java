package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.esotericsoftware.minlog.Log;
import com.keenant.secutor.SecutorApp;
import com.keenant.secutor.SecutorArgs;
import com.keenant.secutor.utils.SimpleLogger;
import java.io.IOException;

public class DesktopLauncher {
  @Parameter(names = {"--help", "-help"}, help = true, description = "Display command help")
  private boolean help;

  /**
   * Main method for the desktop application. Configures logging, launches the app
   * with configuration based on arguments provided.
   * @param args args to parse
   */
  public static void main(String[] args) {
    Log.setLogger(new SimpleLogger());

    Log.info("Secutor", "DesktopLauncher starting...");

    // store args into config/launcher
    SecutorArgs config = new SecutorArgs();
    DesktopLauncher launcher = new DesktopLauncher();
    JCommander commander = JCommander.newBuilder()
        .addObject(launcher)
        .addObject(config)
        .build();
    try {
      commander.parse(args);
    } catch (ParameterException e) {
      e.printStackTrace();
      e.usage();
      return;
    }

    // display help instead of launching app
    if (launcher.help) {
      commander.usage();
      return;
    }

    // run the game!
    launcher.run(config);
  }

  /**
   * Execute the desktop lwjgl3 application.
   * @param args
   */
  private void run(SecutorArgs args) {
    // configure lwjgl3
    Lwjgl3ApplicationConfiguration lwjglConfig = new Lwjgl3ApplicationConfiguration();
    lwjglConfig.setTitle("Secutor");
    lwjglConfig.setInitialVisible(!args.isHeadless());
    lwjglConfig.setWindowedMode(1280, 720);
    lwjglConfig.useVsync(args.isVsync());

    // launch app
    new Lwjgl3Application(new SecutorApp(args), lwjglConfig);
  }
}


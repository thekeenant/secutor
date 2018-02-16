package com.keenant.secutor.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.minlog.Log.Logger;
import com.keenant.secutor.SecutorApp;
import com.keenant.secutor.SecutorConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class DesktopLauncher {
  @Parameter(names = {"--help", "-help"}, help = true, description = "Display command help")
  private boolean help;

  public static void main(String[] args) throws IOException {
    Terminal terminal = TerminalBuilder.builder()
        .system(true)
        .build();

    LineReader reader = LineReaderBuilder.builder()
        .terminal(terminal)
        .build();

    Log.setLogger(new Logger() {
      @Override
      public void log(int level, String category, String message, Throwable ex) {
        reader.callWidget(LineReader.CLEAR);
        terminal.writer().println("[" + category + "] " + message);
        reader.callWidget(LineReader.REDRAW_LINE);
        reader.callWidget(LineReader.REDISPLAY);
        terminal.writer().flush();
      }
    });

    new Thread(() -> {
      while (true) {
        String line = null;
        try {
          line = reader.readLine("prompt> ");
        } catch (UserInterruptException e) {
          System.exit(-1);
        } catch (EndOfFileException e) {
          return;
        }
        if (line == null) {
          continue;
        }

        System.out.println(line);
      }
    }).start();


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


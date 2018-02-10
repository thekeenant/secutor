package com.keenant.secutor;

import com.beust.jcommander.Parameter;

public class SecutorConfig {
  @Parameter(names = {"--host", "-h", "-host"}, description = "Host to bind/connect to")
  private String host;

  @Parameter(names = {"--port", "-p", "-port"}, description = "Port to bind/connect to")
  private int port = 24602;

  @Parameter(names = {"--server", "-server"}, description = "Server mode")
  private boolean server;

  @Parameter(names = {"--headless", "-headless"}, description = "Server in headless mode has no player, renders no display")
  private boolean headless;

  @Parameter(names = {"--no-vsync", "-no-vsync"}, description = "Disables vsync")
  private boolean vsync = true;

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public boolean isServer() {
    return server;
  }

  public boolean isHeadless() {
    return headless;
  }

  public boolean isVsync() {
    return vsync;
  }
}

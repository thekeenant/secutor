package com.keenant.secutor.network;

import java.io.IOException;

/**
 * A secutor client/server.
 */
public interface SecutorEndPoint {
  void start() throws IOException;

  void broadcast(Object packet);
}

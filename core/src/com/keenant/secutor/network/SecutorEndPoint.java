package com.keenant.secutor.network;

import java.io.IOException;

public interface SecutorEndPoint {
  void start() throws IOException;

  void broadcast(Object packet);
}

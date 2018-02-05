package com.keenant.secutor.network;

import com.esotericsoftware.kryonet.Connection;
import java.util.UUID;

public class SecutorConnection extends Connection {
  private UUID uuid;

  public SecutorConnection() {

  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }
}

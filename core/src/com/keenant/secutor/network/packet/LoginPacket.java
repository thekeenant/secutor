package com.keenant.secutor.network.packet;

import java.util.UUID;

/**
 * Client requests to login to server.
 */
public class LoginPacket implements Packet {
  private UUID uuid;
  private String name;

  public LoginPacket(UUID uuid, String name) {
    this.uuid = uuid;
    this.name = name;
  }

  public LoginPacket() {

  }

  public UUID getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }
}

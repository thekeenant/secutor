package com.keenant.secutor.network.packet;

import java.util.UUID;

public class LoginPacket implements Packet {
  public UUID uuid;
  public String name;

  public LoginPacket(UUID uuid, String name) {
    this.uuid = uuid;
    this.name = name;
  }

  public LoginPacket() {

  }
}

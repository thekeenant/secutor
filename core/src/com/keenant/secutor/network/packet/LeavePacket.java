package com.keenant.secutor.network.packet;

import java.util.UUID;

public class LeavePacket implements Packet {
  public UUID uuid;

  public LeavePacket(UUID uuid) {
    this.uuid = uuid;
  }

  public LeavePacket() {

  }
}

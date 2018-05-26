package com.keenant.secutor.network.packet;

import java.util.UUID;

/**
 * Disconnect an entity from the server.
 */
public class LeavePacket implements Packet {
  private UUID uuid;

  public LeavePacket(UUID uuid) {
    this.uuid = uuid;
  }

  public LeavePacket() {

  }

  public UUID getUuid() {
    return uuid;
  }
}

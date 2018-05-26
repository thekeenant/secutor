package com.keenant.secutor.network.packet;

/**
 * Adds an entity/client to the server and connected clients.
 */
public class JoinPacket implements Packet {
  public GladiatorPacket gladiator;

  public JoinPacket(GladiatorPacket gladiator) {
    this.gladiator = gladiator;
  }

  public JoinPacket() {

  }
}

package com.keenant.secutor.network.packet;

public class JoinPacket implements Packet {
  public GladiatorPacket gladiator;

  public JoinPacket(GladiatorPacket gladiator) {
    this.gladiator = gladiator;
  }

  public JoinPacket() {

  }
}

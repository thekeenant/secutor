package com.keenant.secutor.network.packet;

import java.util.UUID;

public class AttackPacket implements Packet {
  public UUID uuid;
  public boolean attacking;

  public AttackPacket(UUID uuid, boolean attacking) {
    this.uuid = uuid;
    this.attacking = attacking;
  }

  public AttackPacket() {

  }
}

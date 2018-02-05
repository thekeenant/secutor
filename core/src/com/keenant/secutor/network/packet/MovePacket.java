package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import java.util.UUID;

public class MovePacket implements Packet {
  public UUID uuid;
  public Vector2 movement;

  public MovePacket(UUID uuid, Vector2 movement) {
    this.uuid = uuid;
    this.movement = movement;
  }

  public MovePacket() {

  }
}

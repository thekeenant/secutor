package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import java.util.UUID;

public class UpdatePositionPacket implements Packet {
  public UUID uuid;
  public Vector2 position;

  public UpdatePositionPacket(UUID uuid, Vector2 position) {
    this.uuid = uuid;
    this.position = position;
  }

  public UpdatePositionPacket() {

  }
}

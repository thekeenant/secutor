package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import java.util.UUID;

public class EntityMovePacket implements Packet {
  private UUID uuid;
  private Vector2 position;

  public EntityMovePacket(UUID uuid, Vector2 position) {
    this.uuid = uuid;
    this.position = position;
  }

  public EntityMovePacket() {

  }

  public Vector2 getPosition() {
    return position;
  }

  public UUID getUuid() {
    return uuid;
  }
}

package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.utils.Direction;
import java.util.UUID;

/**
 * Moves an entity in the world.
 */
public class EntityMovePacket implements Packet {
  private UUID uuid;
  private Vector2 position;
  private Direction facing;

  public EntityMovePacket(UUID uuid, Vector2 position, Direction facing) {
    this.uuid = uuid;
    this.position = position;
    this.facing = facing;
  }

  public EntityMovePacket() {

  }

  public UUID getUuid() {
    return uuid;
  }

  public Vector2 getPosition() {
    return position;
  }

  public Direction getFacing() {
    return facing;
  }
}

package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.ClientGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.utils.Direction;
import java.util.UUID;

public class GladiatorPacket implements Packet {
  public UUID uuid;
  public String name;
  public Vector2 position;
  public Vector2 movement;
  public Vector2 velocity;
  public Direction facing;

  public GladiatorPacket(UUID uuid, String name, Vector2 position, Vector2 movement, Vector2 velocity, Direction facing) {
    this.uuid = uuid;
    this.name = name;
    this.position = position;
    this.movement = movement;
    this.velocity = velocity;
    this.facing = facing;
  }

  public GladiatorPacket() {

  }

  public ClientGladiator createClientGladiator(World world) {
    ClientGladiator client = new ClientGladiator(world, uuid, name);
    syncGladiator(client);
    return client;
  }

  public Gladiator createGladiator(World world) {
    Gladiator gladiator = new Gladiator(world, uuid, name);
    syncGladiator(gladiator);
    return gladiator;
  }

  private void syncGladiator(Gladiator gladiator) {
    gladiator.setPosition(position.x, position.y);
    gladiator.setMovement(movement.x, movement.y);
    gladiator.setVelocity(velocity.x, velocity.y);
    gladiator.setFacing(facing);
  }

  public static GladiatorPacket serialize(Entity entity) {
    // TODO: Not entity
    if (!(entity instanceof Gladiator)) {
      return null;
    }

    Gladiator gladiator = (Gladiator) entity;
    return new GladiatorPacket(
        gladiator.getUuid(),
        gladiator.getName(),
        gladiator.getPosition(),
        gladiator.getMovement(),
        gladiator.getVelocity(),
        gladiator.getFacing()
    );
  }
}

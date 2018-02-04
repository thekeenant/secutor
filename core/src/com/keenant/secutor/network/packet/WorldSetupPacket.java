package com.keenant.secutor.network.packet;

import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Server -> Client
 *
 * Response to join packet.
 */
public class WorldSetupPacket implements Packet {
  public List<GladiatorPacket> gladiators;
  public GladiatorPacket client;

  public WorldSetupPacket(List<GladiatorPacket> gladiators, GladiatorPacket client) {
    this.gladiators = gladiators;
    this.client = client;
  }

  public WorldSetupPacket() {

  }

  public List<Gladiator> createGladiators(World world) {
    return gladiators.stream()
        .map(packet -> packet.createGladiator(world))
        .collect(Collectors.toList());
  }

  public static WorldSetupPacket serialize(World world, Gladiator you) {
    List<GladiatorPacket> gladiators = world.getEntities().stream()
        .map(GladiatorPacket::serialize)
        .collect(Collectors.toList());
    return new WorldSetupPacket(gladiators, GladiatorPacket.serialize(you));
  }
}

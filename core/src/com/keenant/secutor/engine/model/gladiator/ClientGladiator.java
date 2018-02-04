package com.keenant.secutor.engine.model.gladiator;

import com.keenant.secutor.engine.controller.gladiator.GladiatorController;
import com.keenant.secutor.engine.controller.gladiator.ClientGladiatorController;
import com.keenant.secutor.engine.model.world.World;
import java.util.UUID;

/**
 * Represents the client's gladiator.
 */
public class ClientGladiator extends Gladiator {
  public ClientGladiator(World world, UUID uuid, String name) {
    super(world, uuid, name);
  }

  @Override
  public GladiatorController createController() {
    return new ClientGladiatorController(this);
  }
}

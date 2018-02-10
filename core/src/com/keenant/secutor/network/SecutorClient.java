package com.keenant.secutor.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.network.packet.EntityMovePacket;
import com.keenant.secutor.network.packet.JoinPacket;
import com.keenant.secutor.network.packet.LeavePacket;
import com.keenant.secutor.network.packet.LoginPacket;
import com.keenant.secutor.network.packet.Packet;
import com.keenant.secutor.network.packet.UpdatePositionPacket;
import com.keenant.secutor.network.packet.WorldSetupPacket;
import com.keenant.secutor.utils.Utils;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SecutorClient extends Listener implements SecutorEndPoint {
  private final Client client = new Client();
  private final Game game;
  private final String host;
  private final int port;

  public SecutorClient(Game game, String host, int port) {
    this.game = game;
    this.host = host;
    this.port = port;
  }

  public void start() throws IOException {
    Packet.register(client);
    client.start();
    client.connect(1000, host, port);
    client.addListener(this);

    performLogin();
  }

  public void performLogin() {
    LoginPacket login = new LoginPacket(UUID.randomUUID(), "Client" + Utils.random().nextInt(100));
    client.sendTCP(login);
  }

  @Override
  public void received(Connection connection, Object object) {
    World world = game.getWorld().orElse(null);

    if (object instanceof WorldSetupPacket) {
      WorldSetupPacket packet = (WorldSetupPacket) object;

      world = new World();
      List<Gladiator> gladiators = packet.createGladiators(world);
      Gladiator client = packet.client.createClientGladiator(world);

      for (Gladiator gladiator : gladiators)
        world.addEntity(gladiator);
      world.addEntity(client);

      game.setCameraTarget(client);
      game.setWorld(world);

      System.out.println("World is setup");
    }
    else if (world != null) {
      if (object instanceof JoinPacket) {
        JoinPacket packet = (JoinPacket) object;

        Gladiator joinedClient = packet.gladiator.createGladiator(world);
        world.addEntity(joinedClient);

        System.out.println("Gladiator joined: " + packet.gladiator.uuid);
      }
      else if (object instanceof LeavePacket) {
        LeavePacket packet = (LeavePacket) object;

        Entity remove = world.getEntity(packet.uuid).orElse(null);

        if (remove != null) {
          world.removeEntity(remove);
        }
      }
      else if (object instanceof EntityMovePacket) {
        EntityMovePacket packet = (EntityMovePacket) object;

        for (Entity entity : world.getEntities()) {
          if (entity instanceof Gladiator) {
            Gladiator gladiator = (Gladiator) entity;

            if (gladiator.getUuid().equals(packet.uuid)) {
              gladiator.setMovement(packet.movement.x, packet.movement.y);
            }
          }
        }
      }
      else if (object instanceof UpdatePositionPacket) {
        UpdatePositionPacket packet = (UpdatePositionPacket) object;
        Entity entity = world.getEntity(packet.uuid).orElse(null);

        if (entity != null) {
          Gladiator gladiator = (Gladiator) entity;
          gladiator.setPosition(packet.position.x, packet.position.y);
        }
      }
    }
  }

  @Override
  public void broadcast(Object packet) {
    client.sendTCP(packet);
  }
}

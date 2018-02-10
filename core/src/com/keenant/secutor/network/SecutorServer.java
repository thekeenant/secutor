package com.keenant.secutor.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.network.packet.EntityMovePacket;
import com.keenant.secutor.network.packet.GladiatorPacket;
import com.keenant.secutor.network.packet.JoinPacket;
import com.keenant.secutor.network.packet.LeavePacket;
import com.keenant.secutor.network.packet.LoginPacket;
import com.keenant.secutor.network.packet.Packet;
import com.keenant.secutor.network.packet.UpdatePositionPacket;
import com.keenant.secutor.network.packet.WorldSetupPacket;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SecutorServer extends Listener implements SecutorEndPoint {
  private final Server server = new Server() {
    @Override
    protected Connection newConnection() {
      return new SecutorConnection();
    }
  };
  private final Game game;
  private final String host;
  private final int port;

  public SecutorServer(Game game, String host, int port) {
    this.game = game;
    this.host = host;
    this.port = port;
  }

  public void start() throws IOException {
    Packet.register(server);
    server.start();
    if (host != null)
      server.bind(new InetSocketAddress(host, port), null);
    else
      server.bind(port);
    server.addListener(this);
  }

  @Override
  public void disconnected(Connection connection) {
    SecutorConnection conn = (SecutorConnection) connection;

    server.sendToAllExceptTCP(conn.getID(), new LeavePacket(conn.getUuid()));

    World world = game.getWorld().orElse(null);
    Entity remove = world.getEntity(conn.getUuid()).orElse(null);

    world.removeEntity(remove);
  }

  @Override
  public void received(Connection connection, Object object) {
    SecutorConnection conn = (SecutorConnection) connection;
    World world = game.getWorld().orElse(null);

    if (world == null)
      return;

    if (object instanceof LoginPacket) {
      LoginPacket packet = (LoginPacket) object;
      conn.setUuid(packet.uuid);

      Gladiator newClient = new Gladiator(world, packet.uuid, packet.name);

      // send existing world data, and the new client
      connection.sendTCP(WorldSetupPacket.serialize(world, newClient));

      // add new client to server world
      world.addEntity(newClient);

      JoinPacket joinPacket = new JoinPacket(GladiatorPacket.serialize(newClient));
      server.sendToAllExceptTCP(conn.getID(), joinPacket);

      System.out.println("Client logged in and added to world: " + packet.uuid);
    }
    else if (object instanceof EntityMovePacket) {
      EntityMovePacket packet = (EntityMovePacket) object;
      for (Entity entity : world.getEntities()) {
        if (entity instanceof Gladiator) {
          Gladiator gladiator = (Gladiator) entity;

          if (gladiator.getUuid().equals(packet.getUuid())) {
            gladiator.setPosition(packet.getPosition().x, packet.getPosition().y);
          }
        }
      }

      server.sendToAllExceptTCP(conn.getID(), object);
    }
    else if (object instanceof UpdatePositionPacket) {
      UpdatePositionPacket packet = (UpdatePositionPacket) object;
      Entity entity = world.getEntity(packet.uuid).orElse(null);

      if (entity != null) {
        Gladiator gladiator = (Gladiator) entity;
        gladiator.setPosition(packet.position.x, packet.position.y);
      }

      server.sendToAllExceptTCP(conn.getID(), object);
    }
  }

  @Override
  public void broadcast(Object packet) {
    server.sendToAllTCP(packet);
  }
}

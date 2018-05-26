package com.keenant.secutor.network;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
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
    Packet.registerPackets(server);
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

    Entity remove = game.getWorld().flatMap(world -> world.getEntity(conn.getUuid())).orElse(null);

    if (remove == null)
      return;

    game.getWorld().ifPresent(world -> world.removeEntity(remove));

    Log.info("Secutor", "Client " + remove.getUuid() + " disconnected.");
  }

  @Override
  public void received(Connection connection, Object object) {
    SecutorConnection conn = (SecutorConnection) connection;
    World world = game.getWorld().orElse(null);

    if (world == null)
      return;

    if (object instanceof LoginPacket) {
      LoginPacket packet = (LoginPacket) object;

      // only allow one login
      if (conn.getUuid() == null) {
        Log.info("Secutor", "Client " + packet.getUuid() + " [" + packet.getName() + "] connecting...");
        conn.setUuid(packet.getUuid());

        Gladiator newClient = new Gladiator(world, packet.getUuid(), packet.getName());

        // send existing world data, and the new client
        connection.sendTCP(WorldSetupPacket.serialize(world, newClient));

        // add new client to server world
        world.addEntity(newClient);

        // send join to clients
        JoinPacket joinPacket = new JoinPacket(GladiatorPacket.serialize(newClient));
        server.sendToAllExceptTCP(conn.getID(), joinPacket);
      }
    }
    else if (object instanceof EntityMovePacket) {
      EntityMovePacket packet = (EntityMovePacket) object;

      Entity entity = world.getEntity(packet.getUuid()).orElse(null);

      if (entity != null) {
        Vector2 position = entity.getPosition();
        Vector2 newPosition = packet.getPosition();
        position.set(newPosition.x, newPosition.y);

        if (entity instanceof Gladiator) {
          ((Gladiator) entity).setFacing(packet.getFacing());
        }
      }

      // relay to clients
      server.sendToAllExceptTCP(conn.getID(), object);
    }
  }

  @Override
  public void broadcast(Object packet) {
    server.sendToAllTCP(packet);
  }
}

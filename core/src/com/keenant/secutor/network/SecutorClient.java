package com.keenant.secutor.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.network.packet.AttackPacket;
import com.keenant.secutor.network.packet.JoinPacket;
import com.keenant.secutor.network.packet.LeavePacket;
import com.keenant.secutor.network.packet.LoginPacket;
import com.keenant.secutor.network.packet.MovePacket;
import com.keenant.secutor.network.packet.Packet;
import com.keenant.secutor.network.packet.UpdatePositionPacket;
import com.keenant.secutor.network.packet.WorldSetupPacket;
import com.keenant.secutor.utils.Utils;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SecutorClient extends Listener {
  private final Client client;
  private final Game game;

  public SecutorClient(Game game) {
    this.game = game;
    client = new Client();
  }

  public void start() throws IOException {
    Packet.register(client);
    client.start();
    client.connect(2000, "localhost", 24602);
    client.addListener(this);

    performLogin();
  }

  public void performLogin() {
    LoginPacket login = new LoginPacket(UUID.randomUUID(), "RandomName" + Utils.random().nextInt(10));
    client.sendTCP(login);
  }

  float a = 0;
  float b = 0;

  public void update() {
    // todo: temp function
    a += Gdx.graphics.getDeltaTime();
    b += Gdx.graphics.getDeltaTime();

    Gladiator player = game.getPlayer().orElse(null);

    if (player == null)
      return;

    if (a > 0.01) {
      a = 0;

      client.sendTCP(new MovePacket(player.getUuid(), player.getMovement()));
    }
    if (b > 0.1) {
      b = 0;

      client.sendTCP(new AttackPacket(player.getUuid(), player.isAttacking()));
      client.sendTCP(new UpdatePositionPacket(player.getUuid(), player.getPosition()));
    }
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

      game.setPlayer(client);
      game.setWorld(world);

      System.out.println("World is setup");
    }
    else if (object instanceof JoinPacket) {
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
    else if (object instanceof MovePacket) {
      MovePacket packet = (MovePacket) object;
      if (world == null)
        return;

      for (Entity entity : world.getEntities()) {
        if (entity instanceof Gladiator) {
          Gladiator gladiator = (Gladiator) entity;

          if (gladiator.getUuid().equals(packet.uuid)) {
            gladiator.setMovement(packet.movement.x, packet.movement.y);
          }
        }
      }
    }
    else if (object instanceof AttackPacket) {
      AttackPacket packet = (AttackPacket) object;
      if (world == null)
        return;

      for (Entity entity : world.getEntities()) {
        if (entity instanceof Gladiator) {
          Gladiator gladiator = (Gladiator) entity;

          if (gladiator.getUuid().equals(packet.uuid)) {
            gladiator.setAttacking(packet.attacking);
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
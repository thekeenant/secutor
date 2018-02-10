package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.model.gladiator.ClientGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.event.EntityMoveEvent;
import com.keenant.secutor.network.SecutorClient;
import com.keenant.secutor.network.SecutorEndPoint;
import com.keenant.secutor.network.SecutorServer;
import com.keenant.secutor.network.packet.EntityMovePacket;
import java.io.IOException;
import java.util.UUID;
import net.engio.mbassy.listener.Handler;

public class SecutorApp extends ApplicationAdapter {
  private final String host;
  private final int port;
  private final boolean server;
  private final boolean headless;

  private Game game;
  private SecutorEndPoint endpoint;

  public SecutorApp(String host, int port, boolean server, boolean headless) {
    this.host = host;
    this.port = port;
    this.server = server;
    this.headless = headless;
  }

  @Override
  public void create () {
    // load assets
    Assets.load();

    // create game controller
    game = new Game();
    game.subscribe(this);

    if (server) {
      World world = new World();
      if (!headless) {
        Gladiator player = new ClientGladiator(world, UUID.randomUUID(), "Server");
        game.setCameraTarget(player);
        world.addEntity(player);
      }
      game.setWorld(world);
      endpoint = new SecutorServer(game, host, port);
    }
    else {
      endpoint = new SecutorClient(game, host, port);
    }

    try {
      endpoint.start();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  @Override
  public void resize(int width, int height) {
    game.onResize(width, height);
  }

  @Handler
  public void onEntityMove(EntityMoveEvent<?> event) {
    endpoint.broadcast(new EntityMovePacket(event.getEntityUuid(), event.getTo()));
  }

  @Override
  public void render() {
    game.render(!headless);
  }

  @Override
  public void dispose() {
  }
}

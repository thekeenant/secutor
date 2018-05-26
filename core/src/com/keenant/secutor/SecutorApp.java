package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.esotericsoftware.minlog.Log;
import com.keenant.secutor.engine.Game;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.ClientGladiator;
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
  private final SecutorArgs config;

  private Game game;
  private SecutorEndPoint endpoint;

  public SecutorApp(SecutorArgs config) {
    this.config = config;
  }

  @Override
  public void create() {
    // load assets
    Assets.load();

    // create game controller
    game = new Game();
    game.subscribe(this);

    if (config.isServer()) {
      Log.info("Secutor", "Server starting...");
      // create new world for server
      World world = new World();
      game.setWorld(world);

      // if not headless, we give the server itself a client
      if (!config.isHeadless()) {
        ClientGladiator player = new ClientGladiator(world, UUID.randomUUID(), "Server");
        game.setPlayer(player);
        world.addEntity(player);
      }

      endpoint = new SecutorServer(game, config.getHost(), config.getPort());
    }
    else {
      Log.info("Secutor", "Client starting...");
      // a client connects to a server and a gladiator is then registered
      endpoint = new SecutorClient(game, config.getHost(), config.getPort());
    }

    // start the server/client running
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
    UUID playerUuid = game.getPlayer().map(Entity::getUuid).orElse(null);
    if (playerUuid != null) {
      endpoint.broadcast(new EntityMovePacket(event.getEntityUuid(), event.getTo(), event.getFacing()));
    }
  }

  @Override
  public void render() {
    // only draw if not in headless mode
    boolean draw = !config.isHeadless();
    game.render(draw);
  }

  @Override
  public void dispose() {
    Assets.dispose();

    System.exit(-1);
  }
}

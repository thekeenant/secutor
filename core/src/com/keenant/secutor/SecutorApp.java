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
  private final SecutorConfig config;

  private Game game;
  private SecutorEndPoint endpoint;

  public SecutorApp(SecutorConfig config) {
    this.config = config;
  }

  @Override
  public void create () {
    // load assets
    Assets.load();

    if (!config.isServer()) {
      Assets.AUDIO_MENU.setLooping(true);
      Assets.AUDIO_MENU.play();
    }

    // create game controller
    game = new Game();
    game.subscribe(this);

    if (config.isServer()) {
      World world = new World();
      if (!config.isHeadless()) {
        Gladiator player = new ClientGladiator(world, UUID.randomUUID(), "Server");
        game.setCameraTarget(player);
        world.addEntity(player);
      }
      game.setWorld(world);
      endpoint = new SecutorServer(game, config.getHost(), config.getPort());
    }
    else {
      endpoint = new SecutorClient(game, config.getHost(), config.getPort());
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
    // only draw if not in headless mode
    boolean draw = !config.isHeadless();
    game.render(draw);
  }

  @Override
  public void dispose() {
  }
}

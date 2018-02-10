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
  private Game game;
  private final SecutorMode mode;
  private SecutorEndPoint endpoint;

  public SecutorApp(SecutorMode mode) {
    this.mode = mode;
  }

  @Override
  public void create () {
    // load assets
    Assets.load();

    // create game controller
    game = new Game();
    game.subscribe(this);

    switch (mode) {
      case CLIENT:
        endpoint = new SecutorClient(game);
        break;
      case SERVER:
        World world = new World();
        game.setWorld(world);
        Gladiator player = new ClientGladiator(world, UUID.randomUUID(), "Server");
        game.setCameraTarget(player);
        world.addEntity(player);
        endpoint = new SecutorServer(game);
        break;
    }

    try {
      endpoint.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void resize(int width, int height) {
    game.onResize(width, height);
  }

  @Handler
  public void onEntityMove(EntityMoveEvent<?> event) {
    System.out.println(event.getMovement());
    endpoint.broadcast(new EntityMovePacket(event.getEntityUuid(), event.getMovement()));
  }

  @Override
  public void render() {
    game.render();
  }

  @Override
  public void dispose() {

  }
}

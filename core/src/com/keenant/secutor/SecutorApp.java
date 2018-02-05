package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.keenant.secutor.engine.controller.game.GameController;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.gladiator.ClientGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.game.GameView;
import com.keenant.secutor.network.SecutorClient;
import com.keenant.secutor.network.SecutorEndPoint;
import com.keenant.secutor.network.SecutorServer;
import com.keenant.secutor.network.packet.AttackPacket;
import com.keenant.secutor.network.packet.MovePacket;
import com.keenant.secutor.network.packet.UpdatePositionPacket;
import java.io.IOException;
import java.util.UUID;

public class SecutorApp extends ApplicationAdapter {
  private GameController controller;
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
    Game game = new Game();
    controller = game.createController();

    switch (mode) {
      case CLIENT:
        endpoint = new SecutorClient(game);
        break;
      case SERVER:
        endpoint = new SecutorServer(game);
        game.setWorld(new World());
        break;
      default:
        World world = new World();
        game.setWorld(world);
        Gladiator player = new ClientGladiator(world, UUID.randomUUID(), "SoloMode");
        game.setPlayer(player);
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
    controller.resize(width, height);
  }



  float a = 0;
  float b = 0;

  public void update() {
    // todo: temp function
    a += Gdx.graphics.getDeltaTime();
    b += Gdx.graphics.getDeltaTime();

    Gladiator player = controller.getModel().getPlayer().orElse(null);

    if (player == null)
      return;

    if (a > 0.01) {
      a = 0;

      endpoint.broadcast(new MovePacket(player.getUuid(), player.getMovement()));
    }
    if (b > 0.1) {
      b = 0;

      endpoint.broadcast(new AttackPacket(player.getUuid(), player.isAttacking()));
      endpoint.broadcast(new UpdatePositionPacket(player.getUuid(), player.getPosition()));
    }
  }

  @Override
  public void render () {
    update();
    controller.render();
  }

  @Override
  public void dispose () {

  }
}

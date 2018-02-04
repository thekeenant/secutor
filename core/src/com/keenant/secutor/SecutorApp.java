package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.keenant.secutor.engine.controller.game.GameController;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.game.GameView;
import com.keenant.secutor.network.SecutorClient;
import com.keenant.secutor.network.SecutorServer;
import java.io.IOException;

public class SecutorApp extends ApplicationAdapter {
  private GameController controller;
  private final boolean host;
  private SecutorServer server;
  private SecutorClient client;

  public SecutorApp(boolean host) {
    this.host = host;
  }

  @Override
  public void create () {


    // load assets
    Assets.load();

    // create game controller
    Game game = new Game();
    controller = game.createController();

    if (host) {
      server = new SecutorServer(game);
      try {
        server.start();
      } catch (IOException e) {
        e.printStackTrace();
      }

      game.setWorld(new World());
    }
    else {
      client = new SecutorClient(game);
      try {
        client.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void resize(int width, int height) {
    controller.resize(width, height);
  }

  @Override
  public void render () {
    if (client != null)
      client.update();
    controller.render();
  }

  @Override
  public void dispose () {

  }
}

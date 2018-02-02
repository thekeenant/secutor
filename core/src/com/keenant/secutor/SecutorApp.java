package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.keenant.secutor.engine.controller.game.GameController;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.view.game.GameView;

public class SecutorApp extends ApplicationAdapter {
  private GameController controller;

  @Override
  public void create () {
    // load assets
    Assets.load();

    Game game = new Game();
    GameView view = new GameView(game);
    controller = new GameController(game, view);
  }

  @Override
  public void resize(int width, int height) {
    controller.resize(width, height);
  }

  @Override
  public void render () {
    controller.render();
  }

  @Override
  public void dispose () {

  }
}

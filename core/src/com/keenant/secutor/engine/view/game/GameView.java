package com.keenant.secutor.engine.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.Assets;
import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.engine.view.world.WorldView;

public class GameView extends AbstractView<Game> {

  public GameView(Game model) {
    super(model);
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(model.getCamera().combined);

    WorldView worldView = model.getWorldController().map(WorldController::getView).orElse(null);

    if (worldView != null) {
      batch.begin();
      worldView.render(batch, deltaTime);
      batch.end();
    }

    if (model.isPaused()) {
      SpriteBatch overlay = new SpriteBatch();
      overlay.begin();
      overlay.setColor(0, 0, 0, 0.5F);
      overlay.draw(Assets.WHITE, 0, 0, 2000, 2000);
      overlay.end();
    }

    SpriteBatch debug = model.getDebug();

    debug.begin();
    Assets.FONT_24.draw(debug, Gdx.graphics.getFramesPerSecond() + " fps", 0, 24);
    if (model.isPaused()) {
      Assets.FONT_24.draw(debug, "Paused", 0, 48);
    }

    Assets.FONT_24.draw(debug, model.getPlayer().map(Gladiator::getPosition).orElse(null) + "", 0, 72);

    debug.end();
  }
}

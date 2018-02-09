package com.keenant.secutor.engine.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.Assets;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.engine.view.world.WorldView;

public class GameView extends AbstractView<Game> {
  private WorldView worldView;

  public GameView(Game model) {
    super(model);
  }

  public void setWorldView(WorldView worldView) {
    this.worldView = worldView;
  }

  @Override
  public void draw(SpriteBatch batch, float deltaTime) {
    Game game = getModel();

    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(game.getCamera().combined);

    if (worldView != null) {
      batch.begin();
      worldView.draw(batch, deltaTime);
      batch.end();
    }

    if (game.isPaused()) {
      SpriteBatch overlay = new SpriteBatch();
      overlay.begin();
      overlay.setColor(0, 0, 0, 0.5F);
      overlay.draw(Assets.WHITE, 0, 0, 2000, 2000);
      overlay.end();
    }

    SpriteBatch debug = game.getDebug();

    debug.begin();
    Assets.FONT_24.getData().setScale(1.0F);
    Assets.FONT_24.draw(debug, Gdx.graphics.getFramesPerSecond() + " fps", 0, 24);
    if (game.isPaused()) {
      Assets.FONT_24.draw(debug, "Paused", 0, 48);
    }

    Assets.FONT_24.draw(debug, game.getPlayer().map(Gladiator::getPosition).orElse(null) + "", 0, 72);

    debug.end();
  }
}

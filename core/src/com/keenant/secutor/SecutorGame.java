package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.keenant.secutor.engine.controller.gladiator.GladiatorController;
import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.engine.view.world.WorldView;

public class SecutorGame extends ApplicationAdapter {
  // 30fps game
  private static final float TIME_STEP = 1f / 30f;

  private static final float VIEW_WIDTH = 160;
  private static final float VIEW_HEIGHT = 90;

  private OrthographicCamera camera;
  private Viewport viewport;
  private SpriteBatch batch;

  private float accumulator;

  World world = new World();
  WorldView worldView;
  WorldController worldController;

  Gladiator player;

  @Override
  public void create () {
    camera = new OrthographicCamera();
    viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
    batch = new SpriteBatch();

    viewport.apply();
    camera.zoom = 1f;

    worldView = new WorldView(world);
    worldController = new WorldController(world, worldView);


    player = new Gladiator();
    GladiatorView playerView = new GladiatorView(player);
    worldController.addController(new GladiatorController(player, playerView));
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void render () {
    float frameTime = Math.min(Gdx.graphics.getDeltaTime(), 0.25f);
    accumulator += frameTime;

    while (accumulator >= TIME_STEP) {
      update(TIME_STEP);

      // clear screen with black
      Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      draw(TIME_STEP);

      accumulator -= TIME_STEP;
    }
  }

  private void update(float deltaTime) {
    camera.position.set(player.getX(), player.getY(), 0);
    worldController.update(deltaTime);
  }

  private void draw(float deltaTime) {
    camera.update();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();

    worldView.render(batch, deltaTime);

    batch.end();
  }

  @Override
  public void dispose () {

  }
}

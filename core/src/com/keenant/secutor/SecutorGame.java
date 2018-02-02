package com.keenant.secutor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.keenant.secutor.engine.controller.gladiator.AIGladiatorController;
import com.keenant.secutor.engine.controller.gladiator.UserGladiatorController;
import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.gladiator.AIGladiator;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.gladiator.GladiatorView;
import com.keenant.secutor.engine.view.world.WorldView;
import java.util.Random;

public class SecutorGame extends ApplicationAdapter {
  private static SecutorGame game;

  private static final float VIEW_WIDTH = 160;
  private static final float VIEW_HEIGHT = 90;

  private OrthographicCamera camera;
  private Viewport viewport;
  private SpriteBatch batch;
  private SpriteBatch debug;

  World world = new World();
  WorldView worldView;
  WorldController worldController;

  Gladiator player;

  public static SecutorGame getGame() {
    return game;
  }

  @Override
  public void create () {
    game = this;

    // load assets
    SecutorAssets.load();

    camera = new OrthographicCamera();
    viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
    batch = new SpriteBatch();
    debug = new SpriteBatch();

    viewport.apply();
    camera.zoom = 1f;

    worldView = new WorldView(world);
    worldController = new WorldController(world, worldView);

    player = new Gladiator(world);
    player.setPosition(50, 50);
    GladiatorView playerView = new GladiatorView(player);
    worldController.addController(new UserGladiatorController(player, playerView));

    for (int i = 0; i < 1; i++) {
      AIGladiator ai = new AIGladiator(world);
      ai.setEnemy(player);
      ai.setPosition(new Random().nextInt(9), new Random().nextInt(5));
      GladiatorView aiView = new GladiatorView(ai);
      worldController.addController(new AIGladiatorController(ai, aiView));
    }
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void render () {
    float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 0.25f);

    update(deltaTime);

    // clear screen with black
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    draw(deltaTime);
  }

  private void update(float deltaTime) {
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      player.setPosition(0, 0);
    }

    camera.position.set(player.getX(), player.getY(), 0);
    worldController.update(deltaTime);
  }

  private void draw(float deltaTime) {
    camera.update();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();

    worldView.render(batch, deltaTime);

    batch.end();

    debug.begin();
    SecutorAssets.FONT_24.draw(debug, Gdx.graphics.getFramesPerSecond() + " fps", 0, 24);
    debug.end();
  }

  @Override
  public void dispose () {

  }
}

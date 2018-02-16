package com.keenant.secutor.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.keenant.secutor.Assets;
import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.ClientGladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.world.WorldView;
import com.keenant.secutor.event.Event;
import com.keenant.secutor.utils.EventBus;
import java.util.Optional;
import net.engio.mbassy.bus.MBassador;

public class Game {
  private static final float VIEW_WIDTH = 160;
  private static final float VIEW_HEIGHT = 90;

  private EventBus bus = new EventBus();

  private OrthographicCamera camera;
  private Viewport viewport;
  private boolean fullscreen;
  private SpriteBatch batch;
  private SpriteBatch debug;

  private WorldController worldController;
  private WorldView worldView;
  private World world;
  private ClientGladiator player;

  public Game() {
    camera = new OrthographicCamera();
    viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
    batch = new SpriteBatch();
    debug = new SpriteBatch();
    viewport.apply();
  }

  public void render(boolean draw) {
    float deltaTime = Gdx.graphics.getDeltaTime();

    if (world != null) {
      if (worldController == null)
        worldController = world.createController();
      worldController.setModel(world);
      worldView = worldController.getView();
    }

    if (Gdx.input.isKeyJustPressed(Keys.F11)) {
      fullscreen = !fullscreen;

      if (fullscreen) {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
      }
      else {
        Gdx.graphics.setWindowedMode(1280, 720);
      }
    }

    if (worldController != null)
      worldController.act(this, deltaTime);

    if (player != null) {
      camera.position.x += (player.getX() - camera.position.x) * deltaTime;
      camera.position.y += (player.getY() - camera.position.y) * deltaTime;
      camera.update();
    }

    if (draw) {
      batch.setProjectionMatrix(camera.combined);

      Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      if (worldView != null) {
        batch.begin();
        worldView.draw(batch, deltaTime);
        batch.end();
      }

      debug.begin();
      Assets.FONT_24.getData().setScale(1.0F);
      Assets.FONT_24.draw(debug, Gdx.graphics.getFramesPerSecond() + " fps", 0, 24);
      debug.end();
    }
  }

  public void onResize(int screenWidth, int screenHeight) {
    viewport.update(screenWidth, screenHeight);
  }

  public <T extends Event> T post(T event) {
    bus.publish(event);
    return event;
  }

  public void subscribe(Object listener) {
    bus.subscribe(listener);
  }

  public void unsubscribe(Object listener) {
    bus.unsubscribe(listener);
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  public SpriteBatch getDebug() {
    return debug;
  }

  public OrthographicCamera getCamera() {
    return camera;
  }

  public Viewport getViewport() {
    return viewport;
  }

  public Optional<World> getWorld() {
    return Optional.ofNullable(world);
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public Optional<ClientGladiator> getPlayer() {
    return Optional.ofNullable(player);
  }

  public void setPlayer(ClientGladiator player) {
    this.player = player;
  }
}

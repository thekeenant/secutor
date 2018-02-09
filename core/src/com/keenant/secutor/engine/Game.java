package com.keenant.secutor.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import java.util.Optional;

public class Game {
  private static final float VIEW_WIDTH = 160;
  private static final float VIEW_HEIGHT = 90;

  private OrthographicCamera camera;
  private Viewport viewport;
  private SpriteBatch batch;
  private SpriteBatch debug;

  private boolean paused;
  private World world;
  private Entity cameraTarget;

  private boolean fullscreen;

  public Game() {
    camera = new OrthographicCamera();
    viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera);
    batch = new SpriteBatch();
    debug = new SpriteBatch();
    setupCamera();
  }

  private void setupCamera() {
    viewport.apply();
    camera.zoom = 1f;
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

  public void setWorld(World world) {
    this.world = world;
  }

  public Optional<World> getWorld() {
    return Optional.ofNullable(world);
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  public Optional<Entity> getCameraTarget() {
    return Optional.ofNullable(cameraTarget);
  }

  public void setCameraTarget(Entity cameraTarget) {
    this.cameraTarget = cameraTarget;
  }

  public boolean isFullscreen() {
    return fullscreen;
  }

  public void setFullscreen(boolean fullscreen) {
    this.fullscreen = fullscreen;
  }
}

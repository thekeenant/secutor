package com.keenant.secutor.engine.controller.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.controller.gladiator.UserGladiatorController;
import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.game.GameView;

public class GameController extends AbstractController<Game, GameView> {
  public GameController(Game model, GameView view) {
    super(model, view);

    World world = new World();
    Gladiator player = new Gladiator(world);
    player.setPosition(0, 0);
    player.setVelocity(5, 5);

    world.addEntity(new UserGladiatorController(player));
    world.makeInteresting(10, player);

    model.setPlayer(player);
    model.setWorldController(new WorldController(world));
  }

  public void render() {
    boolean pausedPressed = Gdx.input.isKeyJustPressed(Keys.ESCAPE);
    if (pausedPressed)
      model.setPaused(!model.isPaused());

    float deltaTime = model.isPaused() ? 0 : Gdx.graphics.getDeltaTime();

    // update and render views
    update(deltaTime);
    view.render(model.getBatch(), deltaTime);
  }

  @Override
  public void update(float deltaTime) {
    if (Gdx.input.isKeyJustPressed(Keys.F11)) {
      model.setFullscreen(!model.isFullscreen());

      if (model.isFullscreen()) {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
      }
      else {
        Gdx.graphics.setWindowedMode(1280, 720);
      }
    }

    // if we aren't paused (basically)
    if (deltaTime > 0) {
      Gladiator player = model.getPlayer().orElse(null);
      OrthographicCamera camera = model.getCamera();

      if (player != null) {
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
          player.setPosition(0, 0);
        }
        camera.position.x += (player.getX() - camera.position.x) * 2 * deltaTime;
        camera.position.y += (player.getY() - camera.position.y) * 2 * deltaTime;
        camera.update();
      }

      model.getWorldController().ifPresent(controller -> controller.update(deltaTime));
    }
  }

  public void resize(int screenWidth, int screenHeight) {
    model.getViewport().update(screenWidth, screenHeight);
  }
}

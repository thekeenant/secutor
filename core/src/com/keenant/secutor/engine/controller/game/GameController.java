package com.keenant.secutor.engine.controller.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.keenant.secutor.engine.controller.AbstractController;
import com.keenant.secutor.engine.controller.world.WorldController;
import com.keenant.secutor.engine.model.game.Game;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.game.GameView;
import com.keenant.secutor.event.Event;
import net.engio.mbassy.bus.MBassador;

public class GameController extends AbstractController<Game, GameView> {
  private WorldController worldController;
  private MBassador<Event> bus = new MBassador<>();

  public GameController(Game model) {
    super(model, new GameView(model));
  }

  public void render() {
    Game model = getModel();
    GameView view = getView();

    World world = model.getWorld().orElse(null);

    if (world != null) {
      if (worldController == null)
        worldController = world.createController();
      worldController.setModel(world);
      view.setWorldView(worldController.getView());
    }

    boolean pausedPressed = Gdx.input.isKeyJustPressed(Keys.ESCAPE);
    if (pausedPressed)
      model.setPaused(!model.isPaused());

    float deltaTime = model.isPaused() ? 0 : Gdx.graphics.getDeltaTime();

    // act and draw views
    act(deltaTime);
    view.draw(model.getBatch(), deltaTime);
  }

  @Override
  public void act(float deltaTime) {
    Game game = getModel();
    GdxAI.getTimepiece().update(deltaTime);

    if (Gdx.input.isKeyJustPressed(Keys.F11)) {
      game.setFullscreen(!game.isFullscreen());

      if (game.isFullscreen()) {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
      }
      else {
        Gdx.graphics.setWindowedMode(1280, 720);
      }
    }

    // if we aren't paused (basically)
    if (deltaTime > 0) {
      Gladiator player = game.getPlayer().orElse(null);
      OrthographicCamera camera = game.getCamera();

      if (player != null) {
        camera.position.x += (player.getX() - camera.position.x) * 2 * deltaTime;
        camera.position.y += (player.getY() - camera.position.y) * 2 * deltaTime;
        camera.update();
      }

      if (worldController != null)
        worldController.act(deltaTime);
    }
  }

  public void resize(int screenWidth, int screenHeight) {
    Game game = getModel();
    game.getViewport().update(screenWidth, screenHeight);
  }
}

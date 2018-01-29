package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.animation.GladiatorAnimationState;
import com.keenant.secutor.animation.GladiatorDownLogic;
import com.keenant.secutor.animation.GladiatorLeftLogic;
import com.keenant.secutor.animation.GladiatorRightLogic;
import com.keenant.secutor.animation.GladiatorRunRightLogic;
import com.keenant.secutor.animation.GladiatorUpLogic;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.engine.view.head.HeadView;
import com.keenant.secutor.tools.GameAnimation;
import com.keenant.secutor.world.Direction;
import java.util.HashMap;
import java.util.Map;

public class GladiatorView extends AbstractView<Gladiator> {
  private HeadView head;

  private final Map<Direction, GameAnimation<GladiatorAnimationState>> animations;
  private final Map<Direction, GameAnimation<GladiatorAnimationState>> runningAnimations;

  private final Texture shield;

  private float stateTime;

  public GladiatorView(Gladiator gladiator) {
    super(gladiator);
    head = new HeadView(gladiator, model.getHead());

    shield = new Texture(Gdx.files.internal("shield/shield_front.png"));

    animations = new HashMap<>();

    TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_down.png")));
    animations.put(
        Direction.DOWN,
        GameAnimation.split(0.5f, texture, 2, PlayMode.LOOP, new GladiatorDownLogic())
    );

    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_up.png")));
    animations.put(
        Direction.UP,
        GameAnimation.split(0.5f, texture, 2, PlayMode.LOOP, new GladiatorUpLogic())
    );

    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_right.png")));
    animations.put(
        Direction.RIGHT,
        GameAnimation.split(0.5f, texture, 2, PlayMode.LOOP, new GladiatorRightLogic())
    );

    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_right.png")));
    animations.put(
        Direction.LEFT,
        GameAnimation.split(0.5f, texture, 2, PlayMode.LOOP, new GladiatorLeftLogic(), true, false)
    );

    runningAnimations = new HashMap<>();


    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_run_down.png")));
    runningAnimations.put(
        Direction.DOWN,
        GameAnimation.split(1f/10f, texture, 5, PlayMode.LOOP, new GladiatorLeftLogic())
    );

    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_run_up.png")));
    runningAnimations.put(
        Direction.UP,
        GameAnimation.split(1f/10f, texture, 5, PlayMode.LOOP, new GladiatorLeftLogic())
    );

    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_run_right.png")));
    runningAnimations.put(
        Direction.RIGHT,
        GameAnimation.split(1f/10f, texture, 5, PlayMode.LOOP, new GladiatorRunRightLogic())
    );

    texture = new TextureRegion(new Texture(Gdx.files.internal("gladiator_run_right.png")));
    runningAnimations.put(
        Direction.LEFT,
        GameAnimation.split(1f/10f, texture, 5, PlayMode.LOOP, new GladiatorRunRightLogic(), true, false)
    );
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    GameAnimation<GladiatorAnimationState> animation = animations.get(model.getFacing());

    if (model.isRunning())
      animation = runningAnimations.get(model.getFacing());


    Vector2 hand = getAnimationLogic().getPositions().getLeftHand();

    boolean behind = model.getFacing() == Direction.UP || model.getFacing() == Direction.RIGHT;

    if (behind)

      batch.draw(shield, model.getX() + hand.x - shield.getWidth() / 2, model.getY() + hand.y - shield.getHeight() / 2 + 1);

    TextureRegion frame = animation.getKeyFrame(stateTime);

    batch.draw(frame, model.getX(), model.getY());

    head.render(batch, deltaTime);

    if (!behind)

      batch.draw(shield, model.getX() + hand.x - shield.getWidth() / 2, model.getY() + hand.y - shield.getHeight() / 2 + 1);

    stateTime += deltaTime;
  }

  public GladiatorAnimationState getAnimationLogic() {
    GameAnimation<GladiatorAnimationState> animation = animations.get(model.getFacing());

    if (model.isRunning())
      animation = runningAnimations.get(model.getFacing());
    
    return animation.getState(stateTime);
  }

  public HeadView getHead() {
    return head;
  }
}

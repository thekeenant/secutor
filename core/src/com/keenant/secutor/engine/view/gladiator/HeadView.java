package com.keenant.secutor.engine.view.gladiator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.gladiator.Head;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.world.Direction;

public class HeadView extends AbstractView<Head> {
  private final Gladiator gladiator;
  private final TextureRegion helmetDown;
  private final TextureRegion helmetUp;
  private final TextureRegion helmetRight;
  private final TextureRegion helmetLeft;

  public HeadView(Gladiator gladiator, Head model) {
    super(model);
    this.gladiator = gladiator;
    this.helmetDown = new TextureRegion(new Texture(Gdx.files.internal("helmet/helmet_down.png")));
    this.helmetUp = new TextureRegion(new Texture(Gdx.files.internal("helmet/helmet_up.png")));
    this.helmetRight = new TextureRegion(new Texture(Gdx.files.internal("helmet/helmet_right.png")));
    this.helmetLeft = new TextureRegion(helmetRight);
    helmetLeft.flip(true, false);
  }

  @Override
  public void render(SpriteBatch batch, float deltaTime) {
    TextureRegion tex = helmetDown;

    if (gladiator.getFacing() == Direction.RIGHT)
      tex = helmetRight;
    if (gladiator.getFacing() == Direction.UP)
      tex = helmetUp;
    if (gladiator.getFacing() == Direction.LEFT)
      tex = helmetLeft;

    batch.draw(tex, model.getX(), model.getY());
  }
}

package com.keenant.secutor.animation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Contains any logic relevant to the animation of a gladiator's body.
 */
public class GladiatorAnimationState {
  // the position of the different parts of the gladiator
  private final Rectangle box;
  private final Vector2 leftHand;
  private final boolean stabbing;

  public GladiatorAnimationState(Rectangle box, Vector2 leftHand, boolean stabbing) {
    this.box = box;
    this.leftHand = leftHand;
    this.stabbing = stabbing;
  }

  public Rectangle getBox() {
    return box;
  }

  public Vector2 getLeftHand() {
    return leftHand;
  }

  public boolean isStabbing() {
    return stabbing;
  }
}

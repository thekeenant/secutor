package com.keenant.secutor.engine.animation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Contains any logic relevant to the animation of a gladiator's body.
 */
public class GladiatorAnimationState {
  // the position of the different parts of the gladiator

  /** where to draw from */
  private final Vector2 origin;

  /** the complete box around the character */
  private final Rectangle box;

  /** the box around the character's feet */
  private final Rectangle feetBox;

  /** the position of the left hand */
  private final Vector2 leftHand;

  /** if the player is currently stabbing */
  private final boolean stabbing;

  public GladiatorAnimationState(Vector2 origin, Rectangle box, Rectangle feetBox, Vector2 leftHand, boolean stabbing) {
    this.origin = origin;
    this.box = box;
    this.feetBox = feetBox;
    this.leftHand = leftHand;
    this.stabbing = stabbing;
  }

  public Rectangle getBox() {
    return box;
  }

  public Rectangle getFeetBox() {
    return feetBox;
  }

  public Vector2 getLeftHand() {
    return leftHand;
  }

  public boolean isStabbing() {
    return stabbing;
  }

  public Vector2 getOrigin() {
    return origin;
  }
}

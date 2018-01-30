package com.keenant.secutor.animation;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.Gladiator;
import com.keenant.secutor.engine.model.gladiator.GladiatorPart;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains any logic relevant to the animation of a gladiator's body.
 */
public class GladiatorAnimationState {
  // the position of the different parts of the gladiator
  private final Map<GladiatorPart, Vector2> parts;
  private final boolean stabbing;

  public GladiatorAnimationState(Map<GladiatorPart, Vector2> parts, boolean stabbing) {
    this.parts = parts;
    this.stabbing = stabbing;
  }

  public static GladiatorAnimationState fromParts(
      Vector2 head,
      Vector2 torso,
      Vector2 legs,
      Vector2 leftFoot,
      Vector2 rightFoot,
      Vector2 leftHand,
      Vector2 rightHand,
      boolean stabbing
  ) {
    Map<GladiatorPart, Vector2> parts = new HashMap<>();
    parts.put(GladiatorPart.HEAD, head);
    parts.put(GladiatorPart.TORSO, torso);
    parts.put(GladiatorPart.LEGS, legs);
    parts.put(GladiatorPart.LEFT_FOOT, leftFoot);
    parts.put(GladiatorPart.RIGHT_FOOT, rightFoot);
    parts.put(GladiatorPart.LEFT_HAND, leftHand);
    parts.put(GladiatorPart.RIGHT_HAND, rightHand);
    return new GladiatorAnimationState(parts, stabbing);
  }

  public Map<GladiatorPart, Vector2> getParts() {
    return parts;
  }

  public boolean isStabbing() {
    return stabbing;
  }
}

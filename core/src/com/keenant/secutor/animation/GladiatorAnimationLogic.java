package com.keenant.secutor.animation;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.tools.GameAnimation.AnimationLogic;

/**
 * Dictates the logic for gladiator animations.
 */
public class GladiatorAnimationLogic {
  private static final Vector2 HEAD = new Vector2(2, 12);
  private static final Vector2 TORSO = new Vector2(2, 6);
  private static final Vector2 LEGS = new Vector2(2, 1);

  public static AnimationLogic<GladiatorAnimationState> DOWN = (index) -> {
    Vector2 leftFoot = new Vector2(2, 0);
    Vector2 rightFoot = new Vector2(5, 0);

    Vector2 leftHand = new Vector2(7, 6);
    Vector2 rightHand = new Vector2(0, 6);

    if (index == 1) {
      leftHand.add(0, 1);
      rightHand.add(0, 1);
    }

    return GladiatorAnimationState.fromParts(
        HEAD,
        TORSO,
        LEGS,
        leftFoot,
        rightFoot,
        leftHand,
        rightHand,
        false
    );
  };
}

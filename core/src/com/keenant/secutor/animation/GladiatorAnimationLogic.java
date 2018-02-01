package com.keenant.secutor.animation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.utils.GameAnimation.AnimationLogic;

/**
 * Dictates the logic for gladiator animations.
 */
public class GladiatorAnimationLogic {
  public static AnimationLogic<GladiatorAnimationState> DOWN = (index) -> {
    Rectangle box = new Rectangle(4, 0, 8, 16);
    Vector2 rightHand = new Vector2(0, 6);

    if (index == 1) {
      rightHand.add(0, 1);
    }

    return new GladiatorAnimationState(box, rightHand,false);
  };
}

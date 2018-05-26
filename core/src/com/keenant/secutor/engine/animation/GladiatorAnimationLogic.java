package com.keenant.secutor.engine.animation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.utils.GameAnimation.AnimationLogic;

/**
 * Dictates the logic for gladiator animations.
 */
public class GladiatorAnimationLogic {
  public static AnimationLogic<GladiatorAnimationState> DOWN = (index) -> {
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(4, 0, 8, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(0, 6);

    if (index == 1) {
      rightHand.add(0, 1);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand, false);
  };

  public static AnimationLogic<GladiatorAnimationState> DOWN_ATTACK = (index) -> {
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(4, 0, 8, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(0, 6);
    boolean stabbing = false;

    if (index == 1) {
      rightHand.add(0, 1);
    }
    else if (index == 2) {
      rightHand.add(0, 2);
      stabbing = true;
    }
    else if (index == 3) {
      rightHand.add(0, 1);
    }
    else if (index == 4) {
      rightHand.sub(0, 1);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand, stabbing);
  };

  public static AnimationLogic<GladiatorAnimationState> UP = (index) -> {
    // same box as down
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(4, 0, 8, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(7, 6);

    if (index == 1) {
      rightHand.add(0, 1);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand,false);
  };

  public static AnimationLogic<GladiatorAnimationState> UP_ATTACK = (index) -> {
    // same box as down
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(4, 0, 8, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(7, 6);
    boolean stabbing = false;

    if (index == 1) {
      rightHand.add(0, 1);
    }
    if (index == 2) {
      rightHand.add(0, 4);
    }
    if (index == 3) {
      rightHand.add(0, 6);
      stabbing = true;
    }
    if (index == 4) {
      rightHand.add(0, 2);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand, stabbing);
  };

  public static AnimationLogic<GladiatorAnimationState> RIGHT = (index) -> {
    // same box as down
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(6, 0, 4, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(1, 6);

    if (index == 1) {
      rightHand.add(0, 1);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand,false);
  };

  public static AnimationLogic<GladiatorAnimationState> RIGHT_ATTACK = (index) -> {
    // same box as down
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(6, 0, 4, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(1, 6);
    boolean stabbing = false;

    if (index == 1) {
      rightHand.add(1, 1);
    }
    else if (index == 2) {
      rightHand.add(2, 2);
    }
    else if (index == 3) {
      rightHand.add(4, 3);
      stabbing = true;
    }
    else if (index == 4) {
      rightHand.add(2, 2);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand, stabbing);
  };

  public static AnimationLogic<GladiatorAnimationState> LEFT = (index) -> {
    // same box as down
    Vector2 origin = new Vector2(6, 0);
    Rectangle box = new Rectangle(6, 0, 4, 16);
    Rectangle feetBox = new Rectangle(0, 0, 4, 1);
    Vector2 rightHand = new Vector2(2, 6);

    if (index == 1) {
      rightHand.add(0, 1);
    }

    return new GladiatorAnimationState(origin, box, feetBox, rightHand,false);
  };
}

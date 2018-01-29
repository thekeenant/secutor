package com.keenant.secutor.animation;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.tools.GameAnimation.LogicFunction;

public class GladiatorRunRightLogic implements LogicFunction<GladiatorAnimationState> {
  @Override
  public GladiatorAnimationState apply(int index) {
    Vector2 head = new Vector2(2, 12);
    Vector2 torso = new Vector2(2, 6);
    Vector2 legs = new Vector2(2, 1);

    Vector2 leftFoot = new Vector2(2, 0);
    Vector2 rightFoot = new Vector2(5, 0);

    Vector2 leftHand = new Vector2(0, 6);
    Vector2 rightHand = new Vector2(7, 6);

    System.out.println(index);

    if (index >= 0) {
      leftHand.add(1, 1);
      rightHand.add(-1, -1);
    }

    if (index >= 1) {
      leftHand.add(1, 1);
      rightHand.add(-1, 1);
    }

    if (index >= 2) {
      leftHand.add(1, 1);
      rightHand.add(0, 1);
    }

    if (index >= 3) {
      leftHand.add(-1, -1);
      rightHand.add(0, -1);
    }

    if (index >= 4) {
      leftHand.add(-1, -1);
      rightHand.add(1, -1);
    }

    System.out.println(index + " = " + leftHand);
    return new GladiatorAnimationState(new GladiatorPartPositions(head, torso, legs, leftFoot, rightFoot, leftHand, rightHand), false);
  }
}

package com.keenant.secutor.animation;

import com.badlogic.gdx.math.Vector2;
import com.keenant.secutor.engine.model.gladiator.GladiatorPart;
import java.util.HashMap;
import java.util.Map;

public class GladiatorPartPositions {
  private final Map<GladiatorPart, Vector2> positions;

  public GladiatorPartPositions(Vector2 head,
      Vector2 torso,
      Vector2 legs,
      Vector2 leftFoot,
      Vector2 rightFoot,
      Vector2 leftHand,
      Vector2 rightHand) {
    positions = new HashMap<>();
    positions.put(GladiatorPart.HEAD, head);
    positions.put(GladiatorPart.TORSO, torso);
    positions.put(GladiatorPart.LEGS, legs);
    positions.put(GladiatorPart.LEFT_FOOT, leftFoot);
    positions.put(GladiatorPart.RIGHT_FOOT, rightFoot);
    positions.put(GladiatorPart.LEFT_HAND, leftHand);
    positions.put(GladiatorPart.RIGHT_HAND, rightHand);
  }

  public Map<GladiatorPart, Vector2> getMap() {
    return positions;
  }

  public Vector2 getLeftHand() {
    return positions.get(GladiatorPart.LEFT_HAND);
  }

  public Vector2 getRightHand() {
    return positions.get(GladiatorPart.LEFT_HAND);
  }
}

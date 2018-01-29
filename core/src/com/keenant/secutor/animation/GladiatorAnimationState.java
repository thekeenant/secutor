package com.keenant.secutor.animation;

/**
 * Contains any logic relevant to the animation of a gladiator's body.
 */
public class GladiatorAnimationState {
  // the position of the different parts of the gladiator
  private final GladiatorPartPositions positions;
  private final boolean stabbing;

  public GladiatorAnimationState(GladiatorPartPositions positions, boolean stabbing) {
    this.positions = positions;
    this.stabbing = stabbing;
  }

  public GladiatorPartPositions getPositions() {
    return positions;
  }

  public boolean isStabbing() {
    return stabbing;
  }
}

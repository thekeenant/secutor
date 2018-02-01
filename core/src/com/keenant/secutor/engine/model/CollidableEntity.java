package com.keenant.secutor.engine.model;

import com.badlogic.gdx.math.Rectangle;

public interface CollidableEntity {
  Rectangle getBoundingBox();

  default boolean isColliding(CollidableEntity other) {
    return isColliding(other.getBoundingBox());
  }

  default boolean isColliding(CollidableEntity other, float padding) {
    return isColliding(other.getBoundingBox(), padding);
  }

  default boolean isColliding(Rectangle other) {
    return getBoundingBox().overlaps(other);
  }

  default boolean isColliding(Rectangle other, float padding) {
    Rectangle padded = new Rectangle(other);
    padded.x -= padding;
    padded.y -= padding;
    padded.width += padding * 2;
    padded.height += padding * 2;
    return isColliding(padded);
  }
}

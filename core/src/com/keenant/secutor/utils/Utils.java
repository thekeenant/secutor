package com.keenant.secutor.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Utils {
  private static Random random = new Random();

  public static Random random() {
    return random;
  }

  public static void clamp(Vector2 point, Rectangle bounds) {
    float x = MathUtils.clamp(point.x, bounds.getX(), bounds.getX() + bounds.getWidth());
    float y = MathUtils.clamp(point.y, bounds.getY(), bounds.getY() + bounds.getHeight());
    point.set(x, y);
  }

  public static Vector2 nearestPointOnPerimeter(Vector2 point, Rectangle bounds) {
    if (!bounds.contains(point.x, point.y))
      throw new RuntimeException("point must be contained by bounds");


    float x = point.x - bounds.x;
    float y = point.y - bounds.y;
    float width = bounds.width;
    float height = bounds.height;

    float up = height - y;
    float left = x;
    float down = y;
    float right = width - x;

    float min = Math.min(up, Math.min(left, Math.min(down, right)));

    Vector2 result = new Vector2(bounds.x, bounds.y);

    if (up == min) {
      result.add(0, y);
    }
    else if (left == min) {
      result.add(0, y);
    }
    else if (down == min) {
      result.add(x, 0);
    }
    else {
      result.add(x, y);
    }

    return result;
  }
}

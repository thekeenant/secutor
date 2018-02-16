package com.keenant.secutor.engine.view.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.keenant.secutor.Assets;
import com.keenant.secutor.engine.model.Entity;
import com.keenant.secutor.engine.model.world.World;
import com.keenant.secutor.engine.view.AbstractView;
import com.keenant.secutor.engine.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldView extends AbstractView<World> {
  private final Map<Entity, View> views = new HashMap<>();

  public WorldView(World model) {
    super(model);
  }

  public void setView(Entity entity, View view) {
    views.put(entity, view);
  }

  @Override
  public void draw(SpriteBatch batch, float deltaTime) {
    World world = getModel();

    batch.draw(Assets.BACKGROUND, 0, 0);

    List<Entity> entities = new ArrayList<>(world.getEntities());

    // sort by y descending
    entities.sort((e1, e2) -> Float.compare(e2.getY(), e1.getY()));

    for (Entity entity : entities) {
      if (!views.containsKey(entity)) {
        continue;
      }

      views.get(entity).draw(batch, deltaTime);
    }
  }

  public void removeEntity(Entity remove) {
    views.remove(remove);
  }
}

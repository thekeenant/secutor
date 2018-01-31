package com.keenant.secutor.engine.model.world;

import com.keenant.secutor.engine.controller.Controller;
import com.keenant.secutor.engine.model.Model;
import java.util.ArrayList;
import java.util.List;

public class World implements Model {
  private final List<Controller> controllers = new ArrayList<>();


  public List<Controller> getControllers() {
    return controllers;
  }
}

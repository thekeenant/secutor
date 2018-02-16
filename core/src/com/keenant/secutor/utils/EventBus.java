package com.keenant.secutor.utils;

import com.keenant.secutor.event.Event;
import net.engio.mbassy.bus.MBassador;

/**
 * An MBassador event bus with a default error handler.
 */
public class EventBus extends MBassador<Event> {
  public EventBus() {
    super(error -> {
      System.out.println("Publication error: " + error.getMessage());
      System.out.println(error.getPublishedMessage() + " @" + error.getHandler());
      System.exit(-1);
    });
  }

}

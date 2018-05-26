package com.keenant.secutor.utils;

import com.esotericsoftware.minlog.Log.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger extends Logger {
  private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public void log(int level, String category, String message, Throwable ex) {
    StringBuilder builder = new StringBuilder(256);
    builder.append(timeFormat.format(new Date()));
    builder.append(" [");
    builder.append(category);
    builder.append("] \t");
    builder.append(message);
    if (ex != null) {
      StringWriter writer = new StringWriter(256);
      ex.printStackTrace(new PrintWriter(writer));
      builder.append('\n');
      builder.append(writer.toString().trim());
    }
    System.out.println(builder);
  }
}

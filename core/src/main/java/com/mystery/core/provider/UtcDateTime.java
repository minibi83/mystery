package com.mystery.core.provider;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class UtcDateTime {
  
  private final LocalDateTime utcTime;
  
  public UtcDateTime (LocalDateTime localDateTime) {
    localDateTime.atZone( ZoneOffset.UTC);
    this.utcTime = localDateTime;
  }
  
  public static UtcDateTime now() {
    LocalDateTime now=LocalDateTime.now();
    now.atZone( ZoneOffset.UTC);
    return new UtcDateTime(now);
  }
}

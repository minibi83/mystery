package com.mystery.core.travel;

import com.mystery.core.time.LocalDateTimeInterval;
import java.util.List;

public class Travel {
  
  private final LocalDateTimeInterval duration;
  private final List<LocalDateTimeInterval> days;
  
  
  public Travel (LocalDateTimeInterval duration) {
    this.duration=duration;
    this.days = duration.days();
  }
  
}

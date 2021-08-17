package com.mystery.core.time;

import com.mystery.core.time.TimeStamp.Compare;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeStampTest {

  @Test
  public void isValid_true() {
    LocalDateTime now = LocalDateTime.now();
    TimeStamp sut = TimeStamp.of(now);
    assertTrue(sut.isValid());
  }

  @Test
  public void isValid_false() {
    TimeStamp time = TimeStamp.of(null);
    assertFalse(time.isValid());
  }

  @Test
  public void compare_BEFORE() {
    TimeStamp first = TestHelperTimeStamp.of_1();
    TimeStamp second = TestHelperTimeStamp.of_2();
    assertSame(first.compare(second), Compare.BEFORE);
    assertFalse(first.compareTo(second) >= 0);
  }

  @Test
  public void compare_AFTER() {
    TimeStamp first = TestHelperTimeStamp.of_2();
    TimeStamp second = TestHelperTimeStamp.of_1();
    assertSame(first.compare(second), Compare.AFTER);
    assertFalse(first.compareTo(second) <= 0);
  }

  @Test
  public void compare_EQUAL() {
    TimeStamp first = TestHelperTimeStamp.of_1();
    TimeStamp second = TestHelperTimeStamp.of_1();
    assertSame(first.compare(second), Compare.EQUAL);
    assertEquals(first.compareTo(second), 0);
  }
}

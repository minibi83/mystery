package com.mystery.core.time;

import com.mystery.core.time.TimeDuration.Compare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeDurationTest {

  @Test
  public void compare_EQUAL() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.EQUAL);
  }

  @Test
  public void compare_COMPLETE_BEFORE() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_3(), TestHelperTimeStamp.of_4());
    assertSame(first.compare(second), Compare.COMPLETE_BEFORE);
  }

  @Test
  public void compare_COMPLETE_AFTER() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_3(), TestHelperTimeStamp.of_4());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.COMPLETE_AFTER);
  }

  @Test
  public void compare_CONCAT_BEFORE() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3());
    assertSame(first.compare(second), Compare.CONCAT_BEFORE);
  }

  @Test
  public void compare_CONCAT_AFTER() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.CONCAT_AFTER);
  }

  @Test
  public void compare_OVERLAP_END() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_4());
    assertSame(first.compare(second), Compare.OVERLAP_END);
  }

  @Test
  public void compare_OVERLAP_START() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_4());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3());
    assertSame(first.compare(second), Compare.OVERLAP_START);
  }

  @Test
  public void compare_CONTAINS() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_4());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3());
    assertSame(first.compare(second), Compare.CONTAINS);
  }

  @Test
  public void compare_IS_INCLUDED() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_4());
    assertSame(first.compare(second), Compare.IS_INCLUDED);
  }

  @Test
  public void compare_CONTAINS_TILL_END() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3());
    assertSame(first.compare(second), Compare.CONTAINS_TILL_END);
  }

  @Test
  public void compare_IS_INCLUDED_TILL_END() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3());
    assertSame(first.compare(second), Compare.IS_INCLUDED_TILL_END);
  }

  @Test
  public void compare_CONTAINS_FROM_START() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.CONTAINS_FROM_START);
  }

  @Test
  public void compare_IS_INCLUDED_FROM_START() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3());
    assertSame(first.compare(second), Compare.IS_INCLUDED_FROM_START);
  }

  @Test
  public void compare_ERROR_NULL_1() {
    TimeDuration first = TimeDuration.of(null, TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.ERROR);
  }

  @Test
  public void compare_ERROR_NULL_2() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), null);
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.ERROR);
  }

  @Test
  public void compare_ERROR_NULL_3() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(null, TestHelperTimeStamp.of_2());
    assertSame(first.compare(second), Compare.ERROR);
  }

  @Test
  public void compare_ERROR_NULL_4() {
    TimeDuration first = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    TimeDuration second = TimeDuration.of(TestHelperTimeStamp.of_1(), null);
    assertSame(first.compare(second), Compare.ERROR);
  }

  @Test
  public void isValid_true() {
    TimeDuration sut = TimeDuration.of(TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2());
    assertTrue(sut.isValid());
  }

  @Test
  public void isValid_false() {
    TimeDuration first = TimeDuration.of(null, TestHelperTimeStamp.of_2());
    assertFalse(first.isValid());
  }
}

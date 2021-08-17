package com.mystery.core.time;

import com.mystery.core.utils.ComparAble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.mystery.core.time.TimeStamp.Compare.*;

/** Represents a duration class with validation and comparison. */
public interface TimeDuration extends ComparAble<TimeDuration, TimeDuration.Compare> {

  /**
   * Instantiating a TimeDuration
   *
   * @param start start time to set
   * @param end end time to set
   * @return instance of a DefaultTimeDuration
   */
  static TimeDuration of(TimeStamp start, TimeStamp end) {
    return new DefaultTimeDuration(start, end);
  }

  /**
   * Getter for start time
   *
   * @return the start time or null, if it is not set
   */
  TimeStamp getStart();

  /**
   * Getter for end time
   *
   * @return the end time or null, if it is not set
   */
  TimeStamp getEnd();

  //  default TimeLine concat(TimeStampII TimeStampI) {
  //    return TimeLine.of(getStart(), getEnd(), TimeStampI);
  //  }

  /**
   * Compares two TimeDuration with each other
   *
   * @param toCompare the TimeDuration to compare
   * @return the compare result
   */
  default Compare compare(TimeDuration toCompare) {
    if (isInValid() || toCompare.isInValid()) {
      return Compare.ERROR;
    }

    final TimeStamp start = getStart();
    final TimeStamp end = getEnd();
    final TimeStamp otherStart = toCompare.getStart();
    final TimeStamp otherEnd = toCompare.getEnd();

    switch (start.compare(otherStart)) {
      case EQUAL:
        switch (end.compare(otherEnd)) {
          case EQUAL:
            return Compare.EQUAL;
          case AFTER:
            return Compare.CONTAINS_FROM_START;
          case BEFORE:
            return Compare.IS_INCLUDED_FROM_START;
        }
      case AFTER:
        switch (end.compare(otherEnd)) {
          case EQUAL:
            return Compare.IS_INCLUDED_TILL_END;
          case AFTER:
            if (start.compare(otherEnd) == BEFORE) {
              return Compare.OVERLAP_START;
            }
            if (start.compare(otherEnd) == AFTER) {
              return Compare.COMPLETE_AFTER;
            }

            if (start.compare(otherEnd) == EQUAL) {
              return Compare.CONCAT_AFTER;
            }
          case BEFORE:
            return Compare.IS_INCLUDED;
        }
      case BEFORE:
        switch (end.compare(otherEnd)) {
          case EQUAL:
            return Compare.CONTAINS_TILL_END;
          case AFTER:
            return Compare.CONTAINS;
          case BEFORE:
            switch (end.compare(otherStart)) {
              case BEFORE:
                return Compare.COMPLETE_BEFORE;
              case AFTER:
                return Compare.OVERLAP_END;
              case EQUAL:
                return Compare.CONCAT_BEFORE;
            }
        }
    }

    return Compare.ERROR;
  }

  /** @return opposite of {@link #isValid} */
  default boolean isInValid() {
    return !isValid();
  }

  /**
   * @return true, if
   *     <ul>
   *       <li>start time is not null
   *       <li>end time is not null
   *       <li>start is before end
   *       <li>start is equal end
   *     </ul>
   */
  default boolean isValid() {
    if (getStart() == null || getEnd() == null) {
      return false;
    }
    switch (getStart().compare(getEnd())) {
      case BEFORE:
      case EQUAL:
        return true;
      default:
        return false;
    }
  }

  /** Compare result for TimeDuration */
  enum Compare {
    /** items are not valid */
    ERROR,
    /** this is before the other and has no connection */
    COMPLETE_BEFORE,
    /** this is after the other and has no connection */
    COMPLETE_AFTER,
    /** this is before the other and this.end equals other.start */
    CONCAT_BEFORE,
    /** this is after the other and this.start equals other.end */
    CONCAT_AFTER,
    /** this is before the other and this.end is later than other.start */
    OVERLAP_END,
    /** this is after the other and this.start is earlier than other.end */
    OVERLAP_START,
    /**
     * this is bigger than the other and the other is completely in this with no border connection
     */
    CONTAINS,
    /**
     * this is smaller than the other and this is completely in the other with no border connection
     */
    IS_INCLUDED,
    /**
     * this is bigger than the other and the other is completely in this with border connection at
     * end
     */
    CONTAINS_TILL_END,
    /**
     * this is smaller than the other and this is completely in the other with border connection at
     * end
     */
    IS_INCLUDED_TILL_END,
    /**
     * this is bigger than the other and the other is completely in this with border connection at
     * start
     */
    CONTAINS_FROM_START,
    /**
     * this is smaller than the other and this is completely in the other with border connection at
     * start
     */
    IS_INCLUDED_FROM_START,
    /** start end end of both TimeDuration are equal */
    EQUAL
  }

  /** Default implementation of a TimeDuration as a simple data container. */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class DefaultTimeDuration implements TimeDuration {

    private TimeStamp start;
    private TimeStamp end;
  }
}

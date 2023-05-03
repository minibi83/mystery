package com.mystery.core.time;

import com.mystery.core.utils.ComparAble;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 Represents a LocalDateTime wrapper class with validation and comparison.
 */
public interface TimeStamp
    extends Comparable<TimeStamp>, ComparAble<TimeStamp, TimeStamp.Compare> {
  
  /**
   Instantiating a DefaultTimeStamp
   @return instance with current time
   */
  static TimeStamp now () {
    return new DefaultTimeStamp( LocalDateTime.now() );
  }
  
  /**
   Instantiating a TimeStamp
   @param time time to set
   @return instance of a DefaultTimeStamp
   */
  static TimeStamp of (LocalDateTime time) {
    return new DefaultTimeStamp( time );
  }
  
  /**
   Getter for the time
   
   @return the time or null, if it is not set
   */
  LocalDateTime getTime ();
  
  /**
   Compares two TimeStamp with each other
   
   @param toCompare
   the TimeStamp to compare
   @return the compare result
   */
  default Compare compare (TimeStamp toCompare) {
    if ( toCompare == null ) {
      return Compare.ERROR;
    }
    LocalDateTime first=getTime();
    if ( first == null ) {
      return Compare.ERROR;
    }
    LocalDateTime second=toCompare.getTime();
    if ( second == null ) {
      return Compare.ERROR;
    }
    if ( first.isBefore( second ) ) {
      return Compare.BEFORE;
    }
    if ( first.isAfter( second ) ) {
      return Compare.AFTER;
    }
    if ( first.isEqual( second ) ) {
      return Compare.EQUAL;
    }
    return Compare.ERROR;
  }
  
  /**
   @return true, is no time is set.
   */
  default boolean isInvalid () {return !isValid();}
  
  /** @return true, if a time is set */
  default boolean isValid () {
    return getTime() != null;
  }
  
  @Override
  default int compareTo (TimeStamp o) {
    return getTime().compareTo( o.getTime() );
  }
  
  
  
  default TimeStamp getNextDay () {
    LocalDate nextDay=getTime().toLocalDate().plusDays( 1 );
    return TimeStamp.of( nextDay.atStartOfDay() );
  }
  
  default boolean isBefore (TimeStamp other) {
    return Compare.BEFORE.equals(compare( other ));
  }
  
  default boolean isAfter (TimeStamp other) {
    return Compare.AFTER.equals(compare( other ));
  }
  
  /** Compare result for TimeStamp */
  enum Compare {
    ERROR,
    BEFORE,
    AFTER,
    EQUAL
  }
  
  /** Default implementation of a TimeStamp as a simple data container. */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class DefaultTimeStamp
      implements TimeStamp {
    
    private LocalDateTime time;

    @Override
    public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
      return time.format(formatter);
    }
  }
}

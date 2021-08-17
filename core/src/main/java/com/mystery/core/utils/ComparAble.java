package com.mystery.core.utils;

/**
 * Used to make complex comparisons
 *
 * @param <ITEM> the class that should be compared
 * @param <RESULT> the enum to use for the compare result
 */
public interface ComparAble<ITEM, RESULT extends Enum<RESULT>> {

  /**
   * Compares two items and returns the specified compare result
   *
   * @param toCompare the other item
   * @return the compare result
   */
  RESULT compare(ITEM toCompare);
  
}

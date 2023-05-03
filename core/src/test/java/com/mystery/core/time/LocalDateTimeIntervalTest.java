package com.mystery.core.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mystery.core.time.LocalDateTimeInterval.Compare;
import java.util.List;
import org.junit.jupiter.api.Test;

class LocalDateTimeIntervalTest {
  
  @Test
  public void compare_EQUAL () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.EQUAL );
  }
  
  @Test
  public void compare_COMPLETE_BEFORE () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_3(), TestHelperTimeStamp.of_4() );
    assertSame( first.compare( second ), Compare.ABSOLUT_BEFORE );
  }
  
  @Test
  public void compare_COMPLETE_AFTER () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_3(), TestHelperTimeStamp.of_4() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.ABSOLUT_AFTER );
  }
  
  @Test
  public void compare_CONCAT_BEFORE () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3() );
    assertSame( first.compare( second ), Compare.CONNECT_BEFORE );
  }
  
  @Test
  public void compare_CONCAT_AFTER () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.CONNECT_AFTER );
  }
  
  @Test
  public void compare_OVERLAP_END () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_4() );
    assertSame( first.compare( second ), Compare.OVERLAP_AT_END );
  }
  
  @Test
  public void compare_OVERLAP_START () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_4() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3() );
    assertSame( first.compare( second ), Compare.OVERLAP_AT_START );
  }
  
  @Test
  public void compare_CONTAINS () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_4() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3() );
    assertSame( first.compare( second ), Compare.CONTAINS );
  }
  
  @Test
  public void compare_IS_INCLUDED () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_4() );
    assertSame( first.compare( second ), Compare.IS_INCLUDED );
  }
  
  @Test
  public void compare_CONTAINS_TILL_END () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3() );
    assertSame( first.compare( second ), Compare.CONTAINS_TILL_END );
  }
  
  @Test
  public void compare_IS_INCLUDED_TILL_END () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_2(), TestHelperTimeStamp.of_3() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3() );
    assertSame( first.compare( second ), Compare.IS_INCLUDED_TILL_END );
  }
  
  @Test
  public void compare_CONTAINS_FROM_START () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.CONTAINS_FROM_START );
  }
  
  @Test
  public void compare_IS_INCLUDED_FROM_START () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_3() );
    assertSame( first.compare( second ), Compare.IS_INCLUDED_FROM_START );
  }
  
  @Test
  public void compare_ERROR_NULL_1 () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( null, TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.ERROR );
  }
  
  @Test
  public void compare_ERROR_NULL_2 () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), null );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.ERROR );
  }
  
  @Test
  public void compare_ERROR_NULL_3 () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( null, TestHelperTimeStamp.of_2() );
    assertSame( first.compare( second ), Compare.ERROR );
  }
  
  @Test
  public void compare_ERROR_NULL_4 () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    LocalDateTimeInterval second=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), null );
    assertSame( first.compare( second ), Compare.ERROR );
  }
  
  @Test
  public void isValid_true () {
    LocalDateTimeInterval sut=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(), TestHelperTimeStamp.of_2() );
    assertTrue( sut.isValid() );
  }
  
  @Test
  public void isValid_false () {
    LocalDateTimeInterval first=LocalDateTimeInterval.of( null, TestHelperTimeStamp.of_2() );
    assertFalse( first.isValid() );
  }
  
  @Test
  public void days_single () {
    LocalDateTimeInterval sut=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_1_lunch() );
    List<LocalDateTimeInterval> testResult=sut.days();
    LocalDateTimeInterval expectedResult = LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_1_lunch() );
    assertEquals( 1, testResult.size() );
    assertEquals( expectedResult, testResult.get( 0 ) );
  }
  
  @Test
  public void days_nextDay () {
    LocalDateTimeInterval sut=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_2_start() );
    List<LocalDateTimeInterval> testResult=sut.days();
    LocalDateTimeInterval expectedResult = LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_2_start() );
    assertEquals( 1, testResult.size() );
    assertEquals( expectedResult, testResult.get( 0 ) );
  }
  
  @Test
  public void days_nextLunch () {
    LocalDateTimeInterval sut=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_2_lunch() );
    List<LocalDateTimeInterval> testResult=sut.days();
    LocalDateTimeInterval expectedResult1 = LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_2_start() );
    LocalDateTimeInterval expectedResult2 = LocalDateTimeInterval.of( TestHelperTimeStamp.of_2_start(),
        TestHelperTimeStamp.of_2_lunch() );
    assertEquals( 2, testResult.size() );
    assertEquals( expectedResult1, testResult.get( 0 ) );
    assertEquals( expectedResult2, testResult.get( 1 ) );
  }
  
  @Test
  public void days_overNextLunch () {
    LocalDateTimeInterval sut=LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_3_lunch() );
    List<LocalDateTimeInterval> testResult=sut.days();
    LocalDateTimeInterval expectedResult1 = LocalDateTimeInterval.of( TestHelperTimeStamp.of_1(),
        TestHelperTimeStamp.of_2_start() );
    LocalDateTimeInterval expectedResult2 = LocalDateTimeInterval.of( TestHelperTimeStamp.of_2_start(),
        TestHelperTimeStamp.of_3_start() );
    LocalDateTimeInterval expectedResult3 = LocalDateTimeInterval.of( TestHelperTimeStamp.of_3_start(),
        TestHelperTimeStamp.of_3_lunch() );
    assertEquals( 3, testResult.size() );
    assertEquals( expectedResult1, testResult.get( 0 ) );
    assertEquals( expectedResult2, testResult.get( 1 ) );
    assertEquals( expectedResult3, testResult.get( 2 ) );
  }
}

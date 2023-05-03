package com.mystery.core.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyNumberTest {
  
  @Test
  public void testPlus () {
    final MyNumber sut=MyNumber.of( 7 );
    MyNumber testResult=sut.plus( 7 );
    Assertions.assertEquals( MyNumber.of( 14 ), testResult );
  }
  
  @Test
  public void testMinus () {
    final MyNumber sut=MyNumber.of( 8 );
    MyNumber testResult=sut.minus( 7 );
    Assertions.assertEquals( MyNumber.of( 1 ), testResult );
  }
  
  @Test
  public void testMultiply () {
    final MyNumber sut=MyNumber.of( 7 );
    MyNumber testResult=sut.multiply( MyNumber.of( 7 ) );
    Assertions.assertEquals( MyNumber.of( 49 ), testResult );
  }
  
  @Test
  public void testDivide () {
    final MyNumber sut=MyNumber.of( 49 );
    MyNumber testResult=sut.divide( MyNumber.of( 7 ) );
    Assertions.assertEquals( MyNumber.of( 7 ), testResult );
  }
  
  @Test
  public void testDivideSameDenominator () {
    final MyNumber sut=MyNumber.of( 49 ).divide( MyNumber.of( 3 ) );
    final MyNumber testValue = MyNumber.of( 7 ).divide( MyNumber.of( 3 ) );
    MyNumber testResult=sut.divide( MyNumber.of( 7 ) );
    Assertions.assertEquals( MyNumber.of( 7 ), testResult );
  }
  
}
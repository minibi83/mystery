package com.mystery.core.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import lombok.Getter;

@Getter
public class MyNumber {
  
  private final BigInteger numerator;
  private final BigInteger denominator;
  
  private MyNumber (BigInteger numerator, BigInteger denominator) {
    this.numerator=numerator;
    this.denominator=denominator;
  }
  
  public static MyNumber of (int number) {
    return new MyNumber( BigInteger.valueOf( number ), BigInteger.ONE );
  }
  
  public static MyNumber of (long number) {
    return new MyNumber( BigInteger.valueOf( number ), BigInteger.ONE );
  }
  
  public static MyNumber of (long numerator, long denominator) {
    if ( denominator<=0 ) {
      throw new IllegalArgumentException( "Denominator must not be zero or negative." );
    }
    return new MyNumber( BigInteger.valueOf( numerator ), BigInteger.valueOf( denominator ) );
  }
  
  public MyNumber multiply (MyNumber number) {
    return new MyNumber( numerator.multiply( number.getNumerator() ),
        denominator.multiply( number.getDenominator() ) ).fraction();
  }
  
  public MyNumber divide (MyNumber number) {
    MyNumber newNumber;
    if ( number.getNumerator().signum() <0 ) {
      newNumber = number.switchSign();
    } else {
      newNumber = number;
    }
    return new MyNumber( numerator.multiply( newNumber.getDenominator() ),
        denominator.multiply( newNumber.getNumerator() ) ).fraction();
  }
  
  private MyNumber switchSign () {
    return new MyNumber( numerator.negate(), denominator.negate() );
  }
  
  public MyNumber negate () {
    return new MyNumber( numerator.negate(), denominator );
  }
  
  public MyNumber plus(long number) {
    return plus( MyNumber.of( number ) );
  }
  
  public MyNumber plus (MyNumber number) {
    BigInteger extendedNummeratorThis=numerator.multiply( number.getDenominator() );
    BigInteger extendedDenominatorThis=denominator.multiply( number.getDenominator() );
    BigInteger extendedNummeratorOther=number.getNumerator()
        .multiply( denominator );
    
    return new MyNumber( extendedNummeratorThis.add( extendedNummeratorOther ),
        extendedDenominatorThis ).fraction();
  }
  
  public MyNumber minus(long number) {
    return minus( MyNumber.of( number ) );
  }
  
  public MyNumber minus (MyNumber number) {
    BigInteger extendedNummeratorThis=numerator.multiply( number.getDenominator() );
    BigInteger extendedDenominatorThis=denominator.multiply( number.getDenominator() );
    BigInteger extendedNummeratorOther=number.getNumerator()
        .multiply( denominator );
    
    return new MyNumber( extendedNummeratorThis.subtract( extendedNummeratorOther ),
        extendedDenominatorThis ).fraction();
  }
  
  public MyNumber fraction () {
    BigInteger lagestCommonDivisor=getLargestCommonDivisorByEuclid( numerator, denominator );
    if ( BigInteger.ONE.equals( lagestCommonDivisor ) ) { return this; }
    return new MyNumber( numerator.divide( lagestCommonDivisor ),
        denominator.divide( lagestCommonDivisor ) );
  }
  
  private BigInteger getLargestCommonDivisorByEuclid (BigInteger first, BigInteger second) {
    if ( BigInteger.ZERO.equals( second ) ) { return first; }
    return getLargestCommonDivisorByEuclid( second, first.remainder( second ) );
  }
  
  public BigInteger toBigInteger () {
    if ( !BigInteger.ONE.equals( denominator ) ) {
      throw new IllegalStateException( "Denominator must be one." );
    }
    return numerator;
  }
  
  public BigDecimal toBigDecimal () {
    return new BigDecimal( numerator ).divide( new BigDecimal( denominator ),
        MathContext.DECIMAL128 );
  }
  
  @Override
  public boolean equals (Object o) {
    if ( this == o ) { return true; }
    if ( o == null || getClass() != o.getClass() ) { return false; }
    
    MyNumber myNumber=(MyNumber)o;
    
    if ( numerator != null ? !numerator.equals( myNumber.numerator )
        : myNumber.numerator != null ) {
      return false;
    }
    return denominator != null ? denominator.equals( myNumber.denominator )
        : myNumber.denominator == null;
  }
  
  @Override
  public int hashCode () {
    int result=numerator != null ? numerator.hashCode() : 0;
    result=31 * result + (denominator != null ? denominator.hashCode() : 0);
    return result;
  }
}

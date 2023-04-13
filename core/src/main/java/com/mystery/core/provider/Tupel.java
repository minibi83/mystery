package com.mystery.core.provider;

import javax.annotation.processing.Generated;
import lombok.Getter;

@Getter
public class Tupel<A, B> {

  private A a;
  private B b;

  public Tupel (A a, B b) {
    this.a = a;
    this.b = b;
  }

  public Tupel () {}
  
  public static <X ,Y> Tupel<X, Y> of(X a, Y b) {
    return new Tupel<>( a, b );
  }

  public final Tupel<A, B> a (A a) {
    this.a = a;
    return this;
  }

  public final Tupel<A, B> b(B b) {
    this.b = b;
    return this;
  }
}

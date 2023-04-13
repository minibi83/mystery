package com.mystery.core.provider;

public class Tripel<A, B, C> {

  private A a;
  private B b;
  private C c;

  public Tripel (A a, B b, C c) {
    this.a = a;
    this.b = b;
    this.c = c;
    
  }

  public Tripel () {}

  public final A a() {
    return a;
  }

  public final Tripel<A, B, C> a(A a) {
    this.a = a;
    return this;
  }

  public final B b() {
    return b;
  }

  public final Tripel<A, B, C> b(B b) {
    this.b = b;
    return this;
  }
  
  public final C c() {
    return c;
  }
  
  public final Tripel<A, B, C> c(C c) {
    this.c = c;
    return this;
  }
}

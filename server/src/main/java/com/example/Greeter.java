package com.example;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {
} 
/**
   * This is a constructor.
   * @ param string name
   * @ return text
   */
  public final String greet(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}
package com.greenfoxacademy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArgumentHandlerCommandFunctions {

  @Before
  public void before() {
  }

  @Test
  public void testGotArgShouldBeTrueWhenCalledOnManyString() {
    ArgumentHandler argumentHandler = new ArgumentHandler(new String[] {"asd", "qwe", "zxc"});
    Assert.assertTrue(argumentHandler.gotArg());
  }

  @Test
  public void testGotArgShouldBeTrueWhenCalledOnEmptyString() {
    ArgumentHandler argumentHandler = new ArgumentHandler(new String[5]);
    Assert.assertTrue(argumentHandler.gotArg());
  }

  @Test
  public void testGotArgShouldBeFalseWhenCalledOnNull() {
    ArgumentHandler argumentHandler = new ArgumentHandler(null);
    Assert.assertFalse(argumentHandler.gotArg());
  }

  @Test
  public void testGotArgIntShouldBeFalseWhenCalledWithZeroAsArg() {
    ArgumentHandler argumentHandler = new ArgumentHandler(new String[5]);
    Assert.assertFalse(argumentHandler.gotArg(0));
  }

  @Test
  public void testGotArgIntShouldBeFalseWhenCalledWithMinusAsArg() {
    ArgumentHandler argumentHandler = new ArgumentHandler(new String[5]);
    Assert.assertFalse(argumentHandler.gotArg(-5));
  }

  @Test
  public void testGotArgIntShouldBeFalseWhenCalledOnNull() {
    ArgumentHandler argumentHandler = new ArgumentHandler(null);
    Assert.assertFalse(argumentHandler.gotArg(1));
  }
}
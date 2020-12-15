package com.greenfoxacademy;

import org.junit.Assert;
import org.junit.Test;

public class CommandTest {

  @Test
  public void testToString() {
    Command command =
        new Command("-l", "Ki", ArgTypes.NULL, CommandFunctions::listTasks);
    Assert.assertEquals(command.toString(), "\t-l\t[]\t Ki\n");
  }
}
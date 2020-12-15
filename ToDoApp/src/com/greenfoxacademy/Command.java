package com.greenfoxacademy;

import java.util.function.Function;

public class Command {
  private String callArg;
  private String description;
  private ArgTypes argType;
  private String argDescription;
  private Function<String, Boolean> function;

  public Command(String callArg, String description, ArgTypes argType,
                 Function<String, Boolean> function) {
    this.callArg = callArg;
    this.description = description;
    this.argType = argType;
    this.function = function;
    switch (argType) {
      case STRING:
        argDescription = "[Szöveg]";
        break;
      case INDEX:
        argDescription = "[Index]";
        break;
      case NULL:
        argDescription = "[]";
    }
  }

  public Command(String callArg, String description, ArgTypes argType) {
    this.callArg = callArg;
    this.description = description;
    this.argType = argType;
  }

  @Override
  public String toString() {
    return "\t" + this.callArg + "\t" + argDescription + "\t " + this.description + "\n";
  }

  public String getCallArg() {
    return callArg;
  }

  public String getDescription() {
    return description;
  }

  public ArgTypes getArgType() {
    return argType;
  }

  public Function<String, Boolean> getFunction() {
    return function;
  }
}

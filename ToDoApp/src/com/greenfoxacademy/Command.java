package com.greenfoxacademy;

import java.util.function.Function;

public class Command {
  private String callArg;
  private String description;
  private ArgTypes argType;
  private Function<String, Boolean> function;

  public Command(String callArg, String description, ArgTypes argType,
                 Function<String, Boolean> function) {
    this.callArg = callArg;
    this.description = description;
    this.argType = argType;
    this.function = function;
  }

  public Command(String callArg, String description, ArgTypes argType) {
    this.callArg = callArg;
    this.description = description;
    this.argType = argType;
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

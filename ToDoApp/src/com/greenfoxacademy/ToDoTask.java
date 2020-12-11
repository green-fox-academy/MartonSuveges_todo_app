package com.greenfoxacademy;

public class ToDoTask {
  private String name;
  private boolean isDone = false;

  public ToDoTask(String name) {
    this.name = name;
  }

  ToDoTask(String name, boolean isDone) {
    this.name = name;
    this.isDone = isDone;
  }

  public String exportToFile() {
    return (isDone ? 1 : 0) + name;
  }

  @Override
  public String toString() {
    return "[" + (isDone ? "X" : " ") + "] " + name;
  }
}

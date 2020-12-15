package com.greenfoxacademy;

import java.util.ArrayList;

public class ToDoList {
  private ArrayList<ToDoTask> toDoList = new ArrayList<>();

  public ToDoList(ToDoTask... toDoTasks) {
    for (ToDoTask toDoTask : toDoTasks) {
      this.toDoList.add(toDoTask);
    }
  }

  public ToDoList(ArrayList<String> importSource) {
    for (String line : importSource) {
      ToDoTask toDoTask = parseLine(line);
      if (toDoTask != null) {
        toDoList.add(toDoTask);
      }
    }
  }

  public void addTask(String name) {
    toDoList.add(new ToDoTask(name));
  }

  public void removeTask(int index) {
    toDoList.remove(index);
  }

  public void completeTask(int index) {
    toDoList.get(index).completeTask();
  }

  public int size() {
    return toDoList.size();
  }

  public ArrayList<String> exportToFile() {
    ArrayList<String> export = new ArrayList<>();
    for (ToDoTask toDoTask : toDoList) {
      export.add(toDoTask.exportToFile());
    }
    return export;
  }

  @Override
  public String toString() {
    if (toDoList.size() == 0) {
      return "Nincs mára tennivalód! :)";
    } else {
      StringBuilder builder = new StringBuilder();
      for (int i = 1; i <= toDoList.size(); i++) {
        builder.append(i + " - " + toDoList.get(i - 1) + "\n");
      }
      return builder.toString();
    }
  }

  private ToDoTask parseLine(String line) {
    if (line.length() < 1) {
      return null;
    }
    boolean isDone = false;
    String name;
    String firstLetter = line.substring(0, 1);
    if (firstLetter.equals("0") || firstLetter.equals("1")) {
      isDone = firstLetter.equals("0") ? false : true;
      name = line.substring(1);
    } else {
      name = line;
    }
    return new ToDoTask(name, isDone);
  }
}

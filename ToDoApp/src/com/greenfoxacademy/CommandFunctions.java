package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class CommandFunctions {
  private static final String dataRelativePath = "todo.txt";
  private static ToDoList toDoList = new ToDoList(getDataFromFile());

  public static ToDoList getToDoList() {
    return toDoList;
  }

  public static Boolean addTask(String name) {
    toDoList.addTask(name);
    return true;
  }

  public static Boolean removeTask(String indexStr) {
    int index = Integer.parseInt(indexStr) - 1;
    toDoList.removeTask(index);
    return true;
  }

  public static Boolean completeTask(String indexStr) {
    int index = Integer.parseInt(indexStr) - 1;
    toDoList.completeTask(index);
    return true;
  }

  public static Boolean listTasks(String indexStr) {
    System.out.println(toDoList);
    return true;
  }

  private static ArrayList<String> getDataFromFile() {
    Path path = Paths.get(dataRelativePath);
    ArrayList<String> lines;
    try {
      lines = new ArrayList<>(Files.readAllLines(path));
    } catch (IOException e) {
      throw new RuntimeException("Can't read from " + path.toAbsolutePath().toString());
    }
    return lines;
  }

  private static void appendToFile(Iterable output) {
    Path path = Paths.get(dataRelativePath);
    try {
      Files.write(path, output, StandardOpenOption.APPEND);
    } catch (IOException e) {
      throw new RuntimeException("Can't write to " + path.toAbsolutePath().toString());
    }
  }

  private static void writeToFile(Iterable output) {
    Path path = Paths.get(dataRelativePath);
    try {
      Files.write(path, output);
    } catch (IOException e) {
      throw new RuntimeException("Can't write to " + path.toAbsolutePath().toString());
    }
  }

}

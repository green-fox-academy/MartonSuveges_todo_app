package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

public class CommandFunctions {
  private static final String dataRelativePath = "todo.txt";
  
  public static Boolean addTask(String name) {
    String taskName = "\n" + name;
    appendToFile(Collections.singleton(taskName));
    return true;
  }

  public static Boolean removeTask(String indexStr) {
    int index = Integer.parseInt(indexStr) - 1;
    ArrayList<String> lines = getDataFromFile();
    lines.remove(index);
    writeToFile(lines);
    return true;
  }

  public static Boolean completeTask(String[] args) {
    int index = Integer.parseInt(args[1]) - 1;
    ToDoList toDoList = new ToDoList(getDataFromFile());
    toDoList.doTask(index);
    writeToFile(toDoList.exportToFile());
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

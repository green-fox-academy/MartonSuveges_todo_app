package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {

  private static final String dataRelativePath = "todo.txt";
  private static LinkedHashMap<String, String> argDescriptions = new LinkedHashMap<>();
  private static ToDoList toDoList;

  public static void main(String[] args) {
    toDoList = CommandFunctions.getToDoList();
    ArgumentHandler argumentHandler = new ArgumentHandler(args);
    int i = 0;
    while (i < args.length) {
      int checkResult = argumentHandler.checkArg(i, toDoList);
      String[] commandArgs = new String[checkResult + 1];
      if (checkResult < 0) {
        return;
      } else if (checkResult == 0) {
        commandArgs[0] = args[i];
        for (int j = 1; j < commandArgs.length; j++) {
          commandArgs[j] = null;
        }
        argumentHandler.runCommand(commandArgs);
      } else if (checkResult > 0) {
        for (int j = 0; j < commandArgs.length; j++) {
          commandArgs[j] = args[i + j];
        }
        argumentHandler.runCommand(commandArgs);
      }
      i += 1 + checkResult;
    }
    writeToFile(toDoList.exportToFile());
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

  private static void writeToFile(Iterable output) {
    Path path = Paths.get(dataRelativePath);
    try {
      Files.write(path, output);
    } catch (IOException e) {
      throw new RuntimeException("Can't write to " + path.toAbsolutePath().toString());
    }
  }
}

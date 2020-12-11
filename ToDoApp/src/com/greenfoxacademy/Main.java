package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

  private static final String dataRelativePath = "todo.txt";
  private static LinkedHashMap<String, String> argDescriptions = new LinkedHashMap<>();

  public static void main(String[] args) {
    initArgDescriptions();
    if (checkArg(args) != null) {
      ToDoList toDoList = new ToDoList(getDataFromFile());
      if (args[0].equals("-l")) {
        System.out.println(toDoList);
      } else if (args[0].equals("-a")) {
        addTask(args);
      } else if (args[0].equals("-r")) {
        removeTask(args);
      }
    }
  }

  private static void initArgDescriptions() {
    argDescriptions.put("-l", "Kilistázza a feladatokat");
    argDescriptions.put("-a", "Új feladatot ad hozzá");
    argDescriptions.put("-r", "Eltávolít egy feladatot");
    argDescriptions.put("-c", "Teljesít egy feladatot");
  }

  private static void removeTask(String[] args) {
    if (args.length < 2) {
      System.out.println("Nem lehetséges az eltávolítás: nem adott meg indexet!");
      return;
    }

    int index;
    try {
      index = Integer.parseInt(args[1]) - 1;
    } catch (NumberFormatException numberFormatException) {
      System.out.println("Nem lehetséges az eltávolítás: a megadott index nem szám!");
      return;
    }

    ArrayList<String> lines = getDataFromFile();
    if (index >= lines.size()) {
      System.out.println("Nem lehetséges az eltávolítás: túlindexelési probléma adódott!");
      return;
    }

    lines.remove(index);
    writeToFile(lines);
  }

  private static void addTask(String[] args) {

    if (args.length < 2 || args[1].equals("")) {
      System.out.println("Nem lehetséges új feladat hozzáadása: nincs megadva a feladat!");
      return;
    }

    String taskName = "\n" + args[1];
    appendToFile(Collections.singleton(taskName));
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

  public static String checkArg(String[] args) {
    if (args.length != 0) {
      for (String key : argDescriptions.keySet()) {
        if (args[0].equals(key)) {
          return key;
        }
      }
      System.out.println("Nem támogatott argumentum!");
    }
    System.out.println(userInstructions());
    return null;
  }

  public static String userInstructions() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nParancssori Todo applikáció\n");
    builder.append("=============================\n\n");
    builder.append("Parancssori argumentumok:\n");
    for (Map.Entry<String, String> entry : argDescriptions.entrySet()) {
      builder.append("\t")
          .append(entry.getKey())
          .append("\t ")
          .append(entry.getValue())
          .append("\n");
    }
    return builder.toString();
  }
}

package com.greenfoxacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

  private static LinkedHashMap<String, String> argDescriptions = new LinkedHashMap<>();

  public static void main(String[] args) {
    argDescriptions.put("-l", "Kilistázza a feladatokat");
    argDescriptions.put("-a", "Új feladatot ad hozzá");
    argDescriptions.put("-r", "Eltávolít egy feladatot");
    argDescriptions.put("-c", "Teljesít egy feladatot");
    if (args.length == 0) {
      System.out.println(userInstructions());
    } else if (checkArg(args[0]) == null) {
      //TODO: implement unsupported argument handling
    } else if (args[0].equals("-l")) {
      printList();
    }
//    System.out.println("Hello!");
//    System.out.println(Arrays.toString(args));
//
//    Path path = Paths.get("");
//    System.out.println(path.toAbsolutePath());
  }

  private static void printList() {
    Path path = Paths.get("todo.txt");
    ArrayList<String> lines;
    try {
      lines = new ArrayList<>(Files.readAllLines(path));
    } catch (IOException e) {
      throw new RuntimeException("Can't find " + path.toAbsolutePath().toString());
    }

    for (int i = 1; i <= lines.size(); i++) {
      System.out.println(i + " - " + lines.get(i - 1));
    }
  }

  public static String checkArg(String arg) {
    for (String key : argDescriptions.keySet()) {
      if (arg.equals(key)) {
        return key;
      }
    }
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

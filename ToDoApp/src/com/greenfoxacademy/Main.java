package com.greenfoxacademy;

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
    }
//    System.out.println("Hello!");
//    System.out.println(Arrays.toString(args));
//
//    Path path = Paths.get("");
//    System.out.println(path.toAbsolutePath());
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

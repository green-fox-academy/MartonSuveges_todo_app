package com.greenfoxacademy;

import java.util.ArrayList;

public class ArgumentHandler {
  private String[] args;
  private ArrayList<Command> commands = new ArrayList<>();

  public ArgumentHandler(String[] args) {
    this.args = args;
    initArgData();
  }

  public boolean gotArg() {
    return !(args == null || args.length <= 0);
  }

  public boolean gotArg(int thisManyArgs) {
    if (thisManyArgs <= 0) {
      return false;
    }
    return !(args == null || args.length < thisManyArgs);
  }

  public int checkArg(int argIndex, ToDoList toDoList) {
    if (!gotArg(argIndex + 1)) {
      return -1;
    }
    for (int i = 0; i < commands.size(); i++) {
      Command command = commands.get(i);
      if (command.getCallArg().equals(args[argIndex])) {
        switch (command.getArgType()) {
          case NULL:
            return 0;
          case INDEX:
            if (args.length < argIndex + 1) {
              System.out.println("Nem lehetséges a parancs végrehajtása: nem adott meg indexet!");
              return -1;
            }

            int index = -1;
            try {
              index = Integer.parseInt(args[argIndex + 1]) - 1;
            } catch (NumberFormatException numberFormatException) {
              System.out
                  .println("Nem lehetséges a parancs végrehajtása: a megadott index nem szám!");
              return -1;
            }

            if (index > toDoList.size() || index < 0) {
              System.out.println("Nem lehetséges az eltávolítás: túlindexelési probléma adódott!");
              return -1;
            }

            return 1;
          case STRING:
            if (args.length < argIndex + 1 || args[argIndex + 1].equals("")) {
              System.out.println("Nem lehetséges parancs végrehajtása: nincs megadva a feladat!");
              return -1;
            }
            return 1;
        }
      }
    }
    System.out.println("Nem támogatott argumentum!");
    System.out.println(userInstructions());
    return -1;
  }

  public void runCommand(String[] args) {
    for (Command command : commands) {
      if (args[0].equals(command.getCallArg())) {
        if (args.length > 1) {
          command.getFunction().apply(args[1]);
        } else {
          command.getFunction().apply(null);
        }
      }
    }
  }

  private void initArgData() {
    commands.add(new Command(
        "-l", "Kilistázza a feladatokat", ArgTypes.NULL, CommandFunctions::listTasks));
    commands.add(new Command(
        "-a", "Új feladatot ad hozzá", ArgTypes.STRING, CommandFunctions::addTask));
    commands.add(new Command(
        "-r", "Eltávolít egy feladatot", ArgTypes.INDEX, CommandFunctions::removeTask));
    commands.add(new Command(
        "-c", "Teljesít egy feladatot", ArgTypes.INDEX, CommandFunctions::completeTask));
  }

  public String userInstructions() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nParancssori Todo applikáció\n");
    builder.append("=============================\n\n");
    builder.append("Parancssori argumentumok:\n");
    for (Command command : commands) {
      builder.append(command);
    }
    return builder.toString();
  }
}

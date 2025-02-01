package main.java.msrainy.storage;

import main.java.msrainy.command.task.Deadline;
import main.java.msrainy.command.task.Event;
import main.java.msrainy.command.task.Task;
import main.java.msrainy.command.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Populate {
    public static ArrayList<Task> populate(File file) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            List<String> tokens = new ArrayList<>(Arrays.asList(line.split("#")));
            String type = tokens.get(0);
            switch (type) {
                case "T":
                    tasks.add(new ToDo(tokens.get(2), tokens.get(1).equals("true")));
                    break;
                case "D":
                    tasks.add(new Deadline(tokens.get(2), tokens.get(1).equals("true"), tokens.get(3)));
                    break;
                case "E":
                    tasks.add(new Event(tokens.get(2), tokens.get(1).equals("true"), tokens.get(3), tokens.get(4)));
                    break;
                default:
                    System.out.println("\t" + type + ": Unrecognized data entry. Will not be parsed and may be removed");
            }
        }
        return tasks;
    }
}

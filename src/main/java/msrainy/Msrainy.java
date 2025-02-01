package main.java.msrainy;

import main.java.MsrainyException;
import main.java.msrainy.command.Find;
import main.java.msrainy.command.Mark;
import main.java.msrainy.command.ReadList;
import main.java.msrainy.storage.Populate;
import main.java.msrainy.storage.Update;
import main.java.msrainy.ui.Welcome;
import main.java.msrainy.command.Delete;
import main.java.msrainy.command.task.Task;
import main.java.msrainy.command.task.ToDo;
import main.java.msrainy.command.task.Deadline;
import main.java.msrainy.command.task.Event;
import main.java.msrainy.ui.Help;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Msrainy {
    public static void main(String[] args) {
        Welcome.welcome();
        String filename = "./data/tasks.txt";
        File file = new File(filename);
        List<Task> tasks = new ArrayList<Task>();
        try {
            tasks = Populate.populate(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please ensure it is found at: " + filename);
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Task newTask;
        do {
            List<String> tokens = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            String command = tokens.remove(0);
            boolean isOk = true;
            try {
                switch (command) {
                    case "list":
                        ReadList.readTaskList(tasks);
                        break;
                    case "mark":
                        Mark.changeMark(tasks, tokens, true);
                        break;
                    case "unmark":
                        Mark.changeMark(tasks, tokens, false);
                        break;
                    case "delete":
                        Delete.delete(tasks, tokens);
                        break;
                    case "find":
                        Find.find(tasks, tokens);
                        break;
                    case "todo":
                        if (tokens.isEmpty()) {
                            throw new MsrainyException("\tSorry, todo requires a description.");
                        }
                        newTask = new ToDo(String.join(" ", tokens));
                        tasks.add(newTask);
                        break;
                    case "deadline":
                        int byIndex = tokens.indexOf("/by");
                        if (byIndex == -1) {
                            throw new MsrainyException("\tSorry, deadlines require a /by.");
                        }
                        if (byIndex == 0 || byIndex == tokens.size() - 1) {
                            throw new MsrainyException("\tSorry, the description and/or /by fields cannot be empty.");
                        }
                        try {
                            newTask = new Deadline(String.join(" ", tokens.subList(0, byIndex)),
                                    String.join(" ", tokens.subList(byIndex + 1, tokens.size())));
                            tasks.add(newTask);
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter a datetime in the format dd/MM/yyyy HHmm");
                        }
                        break;
                    case "event":
                        int fromIndex = tokens.indexOf("/from");
                        int toIndex = tokens.indexOf("/to");
                        if (fromIndex == -1 || toIndex == -1) {
                            throw new MsrainyException("\tSorry, events require both /from and /to.");
                        }
                        if (toIndex < fromIndex) {
                            throw new MsrainyException("\tSorry, please write /to after /from.");
                        }
                        if (fromIndex == 0 || toIndex == tokens.size() - 1 || toIndex - fromIndex == 1) {
                            throw new MsrainyException("\tSorry, the description, /to, and/or /from fields cannot be empty.");
                        }
                        newTask = new Event(String.join(" ", tokens.subList(0, fromIndex)),
                                String.join(" ", tokens.subList(fromIndex + 1, toIndex)),
                                String.join(" ", tokens.subList(toIndex + 1, tokens.size())));
                        tasks.add(newTask);
                        break;
                    default:
                        Help.commandNotFound();
                }
            } catch (MsrainyException e) {
                System.out.println(e.getMessage());
            }
            if (!Thread.currentThread().isInterrupted() || !isOk) {
                try {
                    Update.update(tasks, filename, command, tokens.get(0));
                } catch (IOException e) {
                    // Actually, this will never happen but oh well
                } catch (IndexOutOfBoundsException e) {
                    // Ditto
                }
            }
            System.out.println("(There are now " + tasks.size() + " tasks.)");
            userInput = scanner.nextLine();
        }
        while (!userInput.equals("bye"));
        System.out.println("\tBye. Hope to see you again soon!");
    }
}

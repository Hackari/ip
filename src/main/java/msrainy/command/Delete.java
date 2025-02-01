package main.java.msrainy.command;

import main.java.msrainy.command.task.Task;

import java.util.List;

public class Delete {
    public static void delete(List<Task> tasks, List<String> tokens) {
        try {
            Task RemovedTask = tasks.remove(Integer.parseInt(tokens.get(0)));
            System.out.println("\tSuccessfully removed: " + RemovedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tThe task you requested for does not exist. Use list to see indices.");
        }
    }
}


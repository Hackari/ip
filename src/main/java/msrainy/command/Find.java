package main.java.msrainy.command;

import main.java.msrainy.command.task.Task;

import java.util.List;

public class Find {
    public static void find(List<Task> tasks, List<String> tokens) {
        boolean found = false;
        if (tokens.isEmpty()) {
            System.out.println("\tPlease enter a keyword");
        }
        String keyword = tokens.get(0);
        if (tasks.isEmpty()) {
            System.out.println("\tThere are no tasks");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            if (description.contains(keyword)) {
                System.out.println("\t" + i + ". " + tasks.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("\tThere are no entries that contain this keyword");
        }
    }
}

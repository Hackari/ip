package main.java.msrainy.command;

import main.java.msrainy.command.task.Task;

import java.util.List;

public class Mark {
    public static void changeMark(List<Task> taskList, List<String> tokens, boolean mark) {
        try {
            int taskIndex = Integer.parseInt(tokens.get(0));
            Task markedTask = taskList.get(taskIndex).mark(mark);
            taskList.set(taskIndex, markedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tThe task you requested for does not exist. Use list to see indices.");
        } catch (NumberFormatException e) {
            System.out.println(("\tPlease add the index of the task you want to operate on."));
        }
    }
}

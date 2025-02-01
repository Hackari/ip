package main.java.msrainy.command;

import main.java.msrainy.command.task.Task;

import java.util.List;

public class Mark {
    public static List<Task> changeMark(List<Task> taskList, List<String> tokens, boolean mark) {
        int taskIndex = Integer.parseInt(tokens.get(0));
        Task markedTask = taskList.get(taskIndex).mark(mark);
        taskList.set(taskIndex, markedTask);
        return taskList;
    }
}

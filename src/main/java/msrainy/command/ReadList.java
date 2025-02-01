package main.java.msrainy.command;

import java.util.List;
import main.java.msrainy.command.task.Task;

public class ReadList {
    public static void readTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) { System.out.println("\tThere are no tasks"); }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + i + ". " + taskList.get(i));
        }
    }
}

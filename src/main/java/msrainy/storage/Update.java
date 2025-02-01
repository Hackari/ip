package main.java.msrainy.storage;

import main.java.msrainy.command.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Update {
    public static void update(List<Task> tasks, String filename, String taskType, String index) throws IOException {
        if (taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline")) {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(tasks.get(tasks.size() - 1).toData() + "\n");
            fw.close();
        } else {
            FileWriter fw = new FileWriter(filename);
            for (Task task : tasks) {
                fw.write(task.toData() + "\n");
            }
            fw.close();
        }
    }
}

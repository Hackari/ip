package main.java.msrainy;

import main.java.msrainy.command.task.Task;
import main.java.msrainy.ui.Ui;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;
    private final Ui ui;

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<Task>();
        this.ui = ui;
    }

    public TaskList(List<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        Task removedTask = tasks.remove(index);
        ui.showMessage("Removing task " + removedTask);
    }

    public void changeMark(int index, boolean mark) throws IndexOutOfBoundsException {
        Task markedTask = tasks.get(index).mark(mark);
        tasks.set(index, markedTask);
    }

    public void print() {
        print("");
    }

    public boolean print(String keyword) {
        boolean found = false;
        if (tasks.isEmpty()) {
            ui.showMessage("\tThere are no tasks");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            if (description.contains(keyword)) {
                System.out.println("\t" + i + ". " + tasks.get(i));
                found = true;
            }
        }
        return found;
    }

    public void add(Task task, Ui ui) {
        ui.showMessage("Added task: " + task);
        tasks.add(task);
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}

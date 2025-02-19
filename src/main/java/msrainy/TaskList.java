package msrainy;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import msrainy.command.task.Task;
import msrainy.ui.Ui;

/**
 * Manages a list of tasks, allowing tasks to be added, removed, modified,
 * and retrieved. This class interacts with the user interface to display
 * task-related messages.
 */
public class TaskList {
    private List<Task> tasks;
    private final Ui ui;

    /**
     * Creates an empty task list.
     *
     * @param ui The user interface instance for displaying messages.
     */
    public TaskList(Ui ui) {
        assert ui != null : "UI instance should not be null";
        this.tasks = new ArrayList<Task>();
        this.ui = ui;
    }

    /**
     * Creates a task list initialized with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the task list with.
     * @param ui    The user interface instance for displaying messages.
     */
    public TaskList(List<Task> tasks, Ui ui) {
        assert tasks != null : "Tasks list should not be null";
        assert ui != null : "UI instance should not be null";
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return A message indicating the removed task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public String remove(int index) throws IndexOutOfBoundsException {
        assert index >= 0 && index < tasks.size() : "Index should be within range";
        Task removedTask = tasks.remove(index);
        return "Removing task: " + removedTask;
    }

    /**
     * Changes the mark status of a task at the specified index.
     *
     * @param index The index of the task to update.
     * @param mark  The new mark status (true for marked, false for unmarked).
     * @return A message indicating the updated mark status.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public String changeMark(int index, boolean mark) throws IndexOutOfBoundsException {
        assert index >= 0 && index < tasks.size() : "Index should be within range";
        Pair<Task, String> markedTaskAndResponse = tasks.get(index).mark(mark);
        tasks.set(index, markedTaskAndResponse.getKey());
        return markedTaskAndResponse.getValue();
    }

    /**
     * Prints all tasks in the list.
     *
     * @return A formatted string of all tasks.
     */
    public String print() {
        return print("");
    }

    /**
     * Prints tasks in the list that contain the specified keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A formatted string of matching tasks.
     */
    public String print(String keyword) {
        if (tasks.isEmpty()) {
            return "There are no tasks now! Great job!";
        }

        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                response.append(i).append(". ").append(task).append("\n");
            }
        }
        if (response.isEmpty()) {
            return "There are no tasks with that keyword!";
        }
        return response.toString().trim();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @return A message confirming the task addition.
     */
    public String add(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
        return "I have added the task: " + task;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}

package msrainy;

import java.util.ArrayList;
import java.util.List;

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
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        Task removedTask = tasks.remove(index);
        ui.showMessage("Removing task " + removedTask);
    }

    /**
     * Changes the mark status of a task at the specified index.
     *
     * @param index The index of the task to update.
     * @param mark The new mark status (true for marked, false for unmarked).
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void changeMark(int index, boolean mark) throws IndexOutOfBoundsException {
        Task markedTask = tasks.get(index).mark(mark);
        tasks.set(index, markedTask);
    }

    /**
     * Prints all tasks in the list. This calls the another function to search
     * for all entries that contain an empty string, which is all entries.
     */
    public void print() {
        print("");
    }

    /**
     * Prints tasks in the list that contain the specified keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return true if matching tasks are found, false otherwise.
     */
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

    /**
     * Adds a task to the list and displays a message.
     *
     * @param task The task to add.
     * @param ui The user interface instance used for displaying messages.
     */
    public void add(Task task, Ui ui) {
        ui.showMessage("Added task: " + task);
        tasks.add(task);
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

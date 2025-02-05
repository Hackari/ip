package msrainy.command.task;

import javafx.util.Pair;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      True if the task is marked as completed, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a task with the given description. The task is initially unmarked.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon indicating whether the task is completed.
     *
     * @return "X" if the task is completed, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks or unmarks the task based on the provided value.
     * Displays a message indicating the status change.
     *
     * @param toMark True to mark the task as completed, false to unmark it.
     * @return The updated task instance.
     */
    public Pair<Task, String> mark(boolean toMark) {
        String response = "";
        if (toMark == isDone) {
            response = description + " is already " + (toMark ? "" : "un") + "marked.";
        } else {
            isDone = toMark;
            response = description + " has been updated:\n" + this;
        }
        return new Pair<>(this, response);
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A string indicating the task status and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted string for storing the task in a file.
     *
     * @return A data string representation of the task.
     */
    public String toData() {
        return isDone + "#" + description;
    }
}

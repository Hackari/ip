package msrainy.command.task;

/**
 * Represents a to-do task without any specific date or time.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo task with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
        System.out.println("Created " + this);
    }

    /**
     * Creates a ToDo task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      True if the task is marked as completed, false otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T#" + super.toData();
    }
}

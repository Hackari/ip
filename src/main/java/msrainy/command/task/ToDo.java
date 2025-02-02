package msrainy.command.task;

/**
 * Represents a to-do task without any specific date or time.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        System.out.println("Created " + this);
    }

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

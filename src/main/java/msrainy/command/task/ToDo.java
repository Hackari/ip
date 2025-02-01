package main.java.msrainy.command.task;

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
    public String toData() {
        return "T#" + super.toData();
    }
}

package msrainy.command.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import msrainy.ui.ParserException;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline task with a specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time in the format "dd/MM/yy HHmm".
     * @throws ParserException If the date format is invalid.
     */
    public Deadline(String description, String by) throws ParserException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
        } catch (DateTimeParseException e) {
            throw new ParserException("Please enter the date in this format: dd/MM/yy HHmm");
        }
        System.out.println("Created " + this);
    }

    /**
     * Creates a Deadline task with a specified description, completion status, and due date.
     *
     * @param description The description of the deadline task.
     * @param isDone      True if the task is marked as completed, false otherwise.
     * @param by          The due date and time in ISO-8601 format.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yy HHmm")) + ")";
    }

    @Override
    public String toData() {
        return "D#" + super.toData() + "#" + by;
    }
}

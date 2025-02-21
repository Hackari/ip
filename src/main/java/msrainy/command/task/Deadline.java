package msrainy.command.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import msrainy.ui.ParserException;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yy HHmm");
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
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParserException("Please enter the date in this format: dd/MM/yy HHmm");
        }
    }

    /**
     * Creates a Deadline task with a specified description, completion status, and due date.
     *
     * @param description The description of the deadline task.
     * @param isDone      True if the task is marked as completed, false otherwise.
     * @param by          The due date and time in ISO-8601 format.
     */
    public Deadline(String description, boolean isDone, String by) throws ParserException {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (Exception e) {
            throw new ParserException("Invalid constructor");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toData() {
        return "D#" + super.toData() + "#" + by;
    }
}

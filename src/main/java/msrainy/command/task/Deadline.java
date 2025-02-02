package msrainy.command.task;

import msrainy.ui.ParserException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws ParserException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
        } catch (DateTimeParseException e) {
            throw new ParserException("Please enter the date in this format: dd/MM/yy HHmm");
        }
        System.out.println("Created " + this);
    }

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

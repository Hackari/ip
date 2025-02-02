package msrainy.command.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import msrainy.ui.ParserException;

/**
 * Represents an event task that occurs within a specific time range.
 */

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event task with a specified description and time range.
     *
     * @param description The description of the event.
     * @param from        The start time in the format "dd/MM/yy HHmm".
     * @param to          The end time in the format "dd/MM/yy HHmm".
     * @throws ParserException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws ParserException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
        } catch (DateTimeParseException e) {
            throw new ParserException("Please enter the date in this format: dd/MM/yy HHmm");
        }
        System.out.println("Created " + this);
    }

    /**
     * Creates an Event task with a specified description, completion status, and time range.
     *
     * @param description The description of the event.
     * @param isDone      True if the event is marked as completed, false otherwise.
     * @param from        The start time in ISO-8601 format.
     * @param to          The end time in ISO-8601 format.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yy HHmm")) + ")";
    }

    @Override
    public String toData() {
        return "E#" + super.toData() + "#" + from + "#" + to;
    }
}

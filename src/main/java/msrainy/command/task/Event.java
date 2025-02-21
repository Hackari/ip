package msrainy.command.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import msrainy.ui.Parser;
import msrainy.ui.ParserException;

/**
 * Represents an event task that occurs within a specific time range.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yy HHmm");

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
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParserException("Please enter the date in this format: dd/MM/yy HHmm");
        }
    }

    /**
     * Creates an Event task with a specified description, completion status, and time range.
     *
     * @param description The description of the event.
     * @param isDone      True if the event is marked as completed, false otherwise.
     * @param from        The start time in ISO-8601 format.
     * @param to          The end time in ISO-8601 format.
     */
    public Event(String description, boolean isDone, String from, String to) throws ParserException {
        super(description, isDone);
        try {
            this.from = LocalDateTime.parse(from);
            this.to = LocalDateTime.parse(to);
        } catch (Exception e) {
            throw new ParserException("Invalid constructor");
        }

    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(OUTPUT_FORMATTER),
                to.format(OUTPUT_FORMATTER));
    }

    /**
     * Converts the event task to a string format suitable for data storage.
     *
     * @return The formatted string representation of the event task.
     */
    @Override
    public String toData() {
        return String.format("E#%s#%s#%s", super.toData(), from, to);
    }
}

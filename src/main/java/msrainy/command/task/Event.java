package msrainy.command.task;

import msrainy.ui.ParserException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that occurs within a specific time range.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

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

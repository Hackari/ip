package msrainy.command;

import msrainy.command.task.Deadline;
import msrainy.ui.ParserException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testValidDeadlineCreation() throws ParserException {
        Deadline deadline = new Deadline("Finish project", "12/02/25 1800");
        assertEquals("[D][ ] Finish project (by: Feb 12 25 1800)", deadline.toString());
    }

    @Test
    public void testValidDeadlineWithCompletion() {
        LocalDateTime dateTime = LocalDateTime.parse("2025-02-12T18:00"); // ISO Format
        Deadline deadline = new Deadline("Submit assignment", true, dateTime.toString());
        assertEquals("[D][X] Submit assignment (by: Feb 12 25 1800)", deadline.toString());
    }

    @Test
    public void testInvalidDateFormat() {
        assertThrows(ParserException.class, () -> new Deadline("Invalid deadline", "12-02-2025 18:00"));
        assertThrows(ParserException.class, () -> new Deadline("Wrong format", "Feb 12 25 1800"));
        assertThrows(ParserException.class, () -> new Deadline("Missing time", "12/02/25"));
    }

    @Test
    public void testToDataFormat() throws ParserException {
        Deadline deadline = new Deadline("Read book", "15/03/24 1200");
        String expectedData = "D#false#Read book#2024-03-15T12:00";
        assertEquals(expectedData, deadline.toData());
    }

    @Test
    public void testMark() throws ParserException {
        Deadline deadline = new Deadline("Complete homework", "20/04/24 2359");
        deadline.mark(true);
        assertEquals("[D][X] Complete homework (by: Apr 20 24 2359)", deadline.toString());
    }
}

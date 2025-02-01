package msrainy.ui;

import msrainy.command.Bye;
import msrainy.command.Delete;
import msrainy.command.Mark;
import msrainy.command.ReadList;
import msrainy.command.Find;
import msrainy.command.Add;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testValidCommands() throws ParserException {
        assertInstanceOf(Bye.class, Parser.parse("bye"));
        assertInstanceOf(ReadList.class, Parser.parse("list"));
        assertInstanceOf(Mark.class, Parser.parse("mark 1"));
        assertInstanceOf(Mark.class, Parser.parse("unmark 1"));
        assertInstanceOf(Delete.class, Parser.parse("delete 1"));
        assertInstanceOf(Find.class, Parser.parse("find keyword"));
        assertInstanceOf(Add.class, Parser.parse("todo buy milk"));
        assertInstanceOf(Add.class, Parser.parse("deadline project /by 12/12/12 1212"));
        assertInstanceOf(Add.class, Parser.parse("event concert /from 12/12/12 1212 /to 12/12/12 1213"));
    }

    @Test
    public void testInvalidCommands() {
        assertThrows(ParserException.class, () -> Parser.parse("unknownCommand"));
        assertThrows(ParserException.class, () -> Parser.parse("someInvalidCommand arg1 arg2"));
    }

    @Test
    public void testMarkUnmarkInvalidIndex() {
        assertThrows(ParserException.class, () -> Parser.parse("mark"));
        assertThrows(ParserException.class, () -> Parser.parse("unmark"));
        assertThrows(NumberFormatException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void testDeleteInvalidIndex() {
        assertThrows(ParserException.class, () -> Parser.parse("delete"));
        assertThrows(NumberFormatException.class, () -> Parser.parse("delete abc"));
    }

    @Test
    public void testTodoWithoutDescription() {
        assertThrows(ParserException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void testDeadlineWithoutBy() {
        assertThrows(ParserException.class, () -> Parser.parse("deadline homework"));
        assertThrows(ParserException.class, () -> Parser.parse("deadline homework /by"));
    }

    @Test
    public void testEventWithoutFromOrTo() {
        assertThrows(ParserException.class, () -> Parser.parse("event party"));
        assertThrows(ParserException.class, () -> Parser.parse("event party /from 12/12/12 1212"));
        assertThrows(ParserException.class, () -> Parser.parse("event party /to 12/12/12 1212"));
    }

    @Test
    public void testEventIncorrectOrderOfFromAndTo() {
        assertThrows(ParserException.class, () -> Parser.parse("event party /to 12/12/12 1212 /from 12/12/12 1213"));
    }

    @Test
    public void testEventEmptyFields() {
        assertThrows(ParserException.class, () -> Parser.parse("event /from /to"));
        assertThrows(ParserException.class, () -> Parser.parse("event meeting /from /to"));
    }
}
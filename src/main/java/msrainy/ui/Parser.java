package msrainy.ui;

import msrainy.command.Command;
import msrainy.command.ReadList;
import msrainy.command.Mark;
import msrainy.command.Delete;
import msrainy.command.Find;
import msrainy.command.Add;
import msrainy.command.Bye;
import msrainy.command.task.ToDo;
import msrainy.command.task.Deadline;
import msrainy.command.task.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parses user input commands and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses the user input command and converts it into a {@code Command} object.
     *
     * @param fullCommand The full command input by the user.
     * @return The corresponding command object.
     * @throws ParserException If the command is malformed or missing required arguments.
     */
    public static Command parse(String fullCommand) throws ParserException {
        List<String> tokens = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));
        String commandType = tokens.remove(0);
        try {
            switch (commandType) {
                case "bye":
                    return new Bye();
                case "list":
                    return new ReadList();
                case "mark":
                    return new Mark(Integer.parseInt(tokens.get(0)), true);
                case "unmark":
                    return new Mark(Integer.parseInt(tokens.get(0)), false);
                case "delete":
                    return new Delete(Integer.parseInt(tokens.get(0)));
                case "find":
                    return new Find(String.join(" ", tokens));
                case "todo":
                    if (tokens.isEmpty()) {
                        throw new ParserException("\tSorry, todos require a description.");
                    }
                    return new Add(new ToDo(String.join(" ", tokens)));
                case "deadline":
                    int byIndex = tokens.indexOf("/by");
                    if (byIndex == -1) {
                        throw new ParserException("\tSorry, deadlines require a /by.");
                    }
                    if (byIndex == 0 || byIndex == tokens.size() - 1) {
                        throw new ParserException("\tSorry, the description and/or /by fields cannot be empty.");
                    }
                    return new Add(new Deadline(String.join(" ", tokens.subList(0, byIndex)),
                            String.join(" ", tokens.subList(byIndex + 1, tokens.size()))));
                case "event":
                    int fromIndex = tokens.indexOf("/from");
                    int toIndex = tokens.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new ParserException("\tSorry, events require both /from and /to.");
                    }
                    if (toIndex < fromIndex) {
                        throw new ParserException("\tSorry, please write /to after /from.");
                    }
                    if (fromIndex == 0 || toIndex == tokens.size() - 1 || toIndex - fromIndex == 1) {
                        throw new ParserException("\tSorry, the description, /to, and/or /from fields cannot be empty.");
                    }
                    return new Add(new Event(String.join(" ", tokens.subList(0, fromIndex)),
                            String.join(" ", tokens.subList(fromIndex + 1, toIndex)),
                            String.join(" ", tokens.subList(toIndex + 1, tokens.size()))));
                default:
                    Ui.commandNotFound();
                    throw new ParserException("Unknown command type: " + commandType);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ParserException("Please supply an index to perform this command.");
        }
    }
}

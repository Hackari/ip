package msrainy.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import msrainy.command.Add;
import msrainy.command.Bye;
import msrainy.command.Command;
import msrainy.command.Delete;
import msrainy.command.Find;
import msrainy.command.Help;
import msrainy.command.Mark;
import msrainy.command.ReadList;
import msrainy.command.task.Deadline;
import msrainy.command.task.Event;
import msrainy.command.task.ToDo;

/**
 * Parses user input commands and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses the user input command and converts it into a {@code Command} object.
     *
     * @param fullCommand The full command input by the user.
     * @return The corresponding {@code Command} object.
     * @throws ParserException If the command is malformed or missing required arguments.
     */
    public static Command parse(String fullCommand) throws ParserException {
        assert fullCommand != null : "fullCommand should not be null";
        assert !fullCommand.trim().isEmpty() : "fullCommand should not be empty";

        List<String> tokens = new ArrayList<>(Arrays.asList(fullCommand.trim()
                .replaceAll("\\s{2,}", " ").split(" ")));
        String commandType = tokens.remove(0);
        try {
            switch (commandType) {
            case "bye":
                return new Bye();
            case "list":
                return new ReadList();
            case "mark":
                return new Mark(parseIndex(tokens), true);
            case "unmark":
                return new Mark(parseIndex(tokens), false);
            case "delete":
                return new Delete(parseIndex(tokens));
            case "find":
                return new Find(String.join(" ", tokens));
            case "todo":
                return parseToDo(tokens);
            case "deadline":
                return parseDeadline(tokens);
            case "event":
                return parseEvent(tokens);
            case "help":
                return new Help();
            default:
                throw new ParserException("I do not understand: "
                        + commandType
                        + "\n "
                        + "Please run Help for a list of commands!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ParserException("Please supply an index!");
        }
    }

    /**
     * Parses and retrieves an index from the provided list of tokens.
     *
     * @param tokens The list of tokens extracted from user input.
     * @return The parsed integer index.
     * @throws ParserException If the index is missing or invalid.
     */
    private static int parseIndex(List<String> tokens) throws ParserException {
        if (tokens.isEmpty()) {
            throw new ParserException("Please supply an index!");
        }
        return Integer.parseInt(tokens.get(0));
    }

    /**
     * Parses a ToDo command.
     *
     * @param tokens The list of tokens extracted from user input.
     * @return The corresponding {@code Add} command with a {@code ToDo} task.
     * @throws ParserException If the description is missing.
     */
    private static Command parseToDo(List<String> tokens) throws ParserException {
        if (tokens.isEmpty()) {
            throw new ParserException("Give me a description!.");
        }
        return new Add(new ToDo(String.join(" ", tokens)));
    }

    /**
     * Parses a Deadline command.
     *
     * @param tokens The list of tokens extracted from user input.
     * @return The corresponding {@code Add} command with a {@code Deadline} task.
     * @throws ParserException If the description or deadline time is missing.
     */
    private static Command parseDeadline(List<String> tokens) throws ParserException {
        int byIndex = tokens.indexOf("/by");
        if (byIndex == -1 || byIndex == 0 || byIndex == tokens.size() - 1) {
            throw new ParserException("Give me a description and a /by time!");
        }
        return new Add(new Deadline(
                String.join(" ", tokens.subList(0, byIndex)),
                String.join(" ", tokens.subList(byIndex + 1, tokens.size()))
        ));
    }

    /**
     * Parses an Event command.
     *
     * @param tokens The list of tokens extracted from user input.
     * @return The corresponding {@code Add} command with an {@code Event} task.
     * @throws ParserException If the description or event timing details are missing or malformed.
     */
    private static Command parseEvent(List<String> tokens) throws ParserException {
        int fromIndex = tokens.indexOf("/from");
        int toIndex = tokens.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex || fromIndex == 0 || toIndex == tokens.size() - 1) {
            throw new ParserException("Give me a description, a /from time, and a /to time in that order!");
        }
        return new Add(new Event(
                String.join(" ", tokens.subList(0, fromIndex)),
                String.join(" ", tokens.subList(fromIndex + 1, toIndex)),
                String.join(" ", tokens.subList(toIndex + 1, tokens.size()))
        ));
    }
}

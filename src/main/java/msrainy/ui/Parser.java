package main.java.msrainy.ui;

import main.java.msrainy.command.Command;
import main.java.msrainy.command.ReadList;
import main.java.msrainy.command.Mark;
import main.java.msrainy.command.Delete;
import main.java.msrainy.command.Find;
import main.java.msrainy.command.Add;
import main.java.msrainy.command.Bye;
import main.java.msrainy.command.task.ToDo;
import main.java.msrainy.command.task.Deadline;
import main.java.msrainy.command.task.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Command parse(String fullCommand) throws ParserException {
        List<String> tokens = new ArrayList<String>(Arrays.asList(fullCommand.split(" ")));
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

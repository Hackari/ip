package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents an executable command within the application.
 * Subclasses must implement the {@code execute} method to define command behavior.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage handler.
     *
     * @param tasks   The task list on which the command operates.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to update saved tasks if needed.
     * @return the response string.
     * @throws IOException If an I/O error occurs while executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command that prints the list of tasks.
 */
public class ReadList extends Command {
    /**
     * Executes the command to print the list of tasks.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The user interface to handle output.
     * @param storage The storage handler (not used in this command).
     * @return A string representation of the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.print();
    }
}

package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command that exits the program.
 */
public class Bye extends Command {
    /**
     * Executes the command to display a farewell message and terminate the program.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display the farewell message.
     * @param storage The storage handler (not used in this command).
     * @return true, indicating that the program should terminate.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        return true;
    }
}

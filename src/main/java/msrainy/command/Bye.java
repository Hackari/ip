package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command that exits the program.
 */
public class Bye extends Command {
    /**
     * Executes the command to display a farewell message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display the farewell message.
     * @param storage The storage handler (not used in this command).
     * @return A farewell message indicating that the program is terminating.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.sayBye();
    }
}

package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command to display help information.
 */
public class Help extends Command {
    /**
     * Executes the help command, displaying available commands to the user.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The user interface to display help messages.
     * @param storage The storage handler (unused in this command).
     * @return A message displaying help information.
     * @throws IOException If an I/O error occurs (though unlikely in this case).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.showHelp();
    }
}

package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

import java.io.IOException;

public class Help extends Command{
    /**
     * Executes the command to add a task to the task list and update storage.
     *
     * @param tasks   The task list to which the task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the task.
     * @return A message confirming the task addition.
     * @throws IOException If an I/O error occurs while updating storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.help();
    }
}
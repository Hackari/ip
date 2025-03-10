package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class Find extends Command {
    private final String keyword;

    /**
     * Creates a Find command with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public Find(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to search for tasks containing the specified keyword.
     * If no matching tasks are found, a message is displayed to inform the user.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface to display results.
     * @param storage The storage handler (not used in this command).
     * @return A string representation of the search results.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.print(keyword);
    }
}

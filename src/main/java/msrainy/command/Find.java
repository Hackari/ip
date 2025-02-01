package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

public class Find extends Command {
    private final String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        boolean found = tasks.print(keyword);
        if (!found) {
            ui.showMessage("\tThere are no entries that contain this keyword");
        }
        return false;
    }
}

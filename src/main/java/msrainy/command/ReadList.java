package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

public class ReadList extends Command {
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.print();
        return false;
    }
}

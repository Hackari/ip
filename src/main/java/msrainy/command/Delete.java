package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;



public class Delete extends Command {
    private final int index;

    public Delete(int index) {
        this.index = index;
    }
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.remove(index);
        storage.update(tasks);
        return false;
    }
}


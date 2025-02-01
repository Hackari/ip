package main.java.msrainy.command;

import main.java.msrainy.TaskList;
import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;

import java.io.IOException;

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


package main.java.msrainy.command;

import main.java.msrainy.TaskList;
import main.java.msrainy.command.task.Task;
import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;

import java.io.IOException;

public class Mark extends Command {
    private final int index;
    private final boolean isMarking;

    public Mark(int index, boolean isMarking) {
        this.index = index;
        this.isMarking = isMarking;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.changeMark(index, isMarking);
        storage.update(tasks);
        return false;
    }
}

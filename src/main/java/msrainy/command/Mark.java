package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;



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

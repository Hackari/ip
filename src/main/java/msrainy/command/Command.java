package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;



public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

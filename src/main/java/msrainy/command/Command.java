package main.java.msrainy.command;

import main.java.msrainy.TaskList;
import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

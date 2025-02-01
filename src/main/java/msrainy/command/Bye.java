package main.java.msrainy.command;

import main.java.msrainy.TaskList;
import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;

public class Bye extends Command {
    public boolean execute(TaskList tasks, Ui ui, Storage storage)  {
        ui.bye();
        return true;
    }
}

package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

public class Bye extends Command {
    public boolean execute(TaskList tasks, Ui ui, Storage storage)  {
        ui.bye();
        return true;
    }
}

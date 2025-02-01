package main.java.msrainy.command;

import main.java.msrainy.TaskList;
import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;

public class Find extends Command {
    private final String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        boolean found = tasks.print(keyword);
        if (!found) {
            ui.showMessage("\tThere are no entries that contain this keyword");
        }
        return false;
    }
}

package ploopy.command;

import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

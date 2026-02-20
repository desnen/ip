package ploopy.command;

import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;

public class ListCommand extends Command{

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

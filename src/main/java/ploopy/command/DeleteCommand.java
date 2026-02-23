package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;


public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException {
        Task t = tasks.give(index - 1);
        tasks.delete(index);
        ui.showDelete(t, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

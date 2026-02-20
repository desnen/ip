package ploopy.command;

import ploopy.PloopyException;
import ploopy.task.Task;
import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException {
        Task t = tasks.give(index - 1);
        tasks.delete(index);
        ui.showDelete(index, t, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

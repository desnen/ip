package ploopy.command;

import ploopy.task.Task;
import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;
import ploopy.PloopyException;

public class MarkCommand extends Command{

    boolean isMark;
    int index;

    public MarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException {
        tasks.mark(isMark, index);
        Task t = tasks.give(index);
        ui.showMark(isMark, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

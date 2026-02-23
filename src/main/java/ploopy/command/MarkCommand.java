package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;

public class MarkCommand extends Command {

    private final boolean isMark;
    private final int index;

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

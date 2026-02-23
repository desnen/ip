package ploopy.command;

import ploopy.PloopyException;
import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command{

    public ListCommand() {}

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

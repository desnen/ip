package ploopy.command;

import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

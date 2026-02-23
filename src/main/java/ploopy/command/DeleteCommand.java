package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;


/**
 * Deletes a task from the task list.
 * Updates the UI to confirm that the task has been removed.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete the tasks from TaskList and prints confirmation message upon successful deletion.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for saving tasks.
     * @throws PloopyException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException {
        Task t = tasks.give(index - 1);
        tasks.delete(index);
        return ui.showDelete(t, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

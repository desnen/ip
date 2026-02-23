package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;

/**
 * Marks or unmarks a task in the task list.
 * Updates the UI to confirm the task's completion status.
 */
public class MarkCommand extends Command {

    private final boolean isMark;
    private final int index;

    /**
     * Constructs a MarkCommand with the specified marking operation and target index.
     *
     * @param isMark True to mark the task as done, false to unmark it.
     * @param index The zero-based index of the task to update.
     */
    public MarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Marks or unmarks task at index in TaskList.
     * Displays confirmation message upon successful marking/unmarking.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for saving tasks.
     * @throws PloopyException If error occurs during execution of command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException {
        tasks.mark(isMark, index);
        Task t = tasks.give(index);
        return ui.showMark(isMark, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

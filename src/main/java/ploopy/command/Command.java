package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;

/**
 * Represents an executable user command.
 * Defines the common interface for commands.
 */
public abstract class Command {
    protected Task task;

    public Command() {}
    public Command(Task task) {
        this.task = task;
    }

    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for saving tasks.
     * @throws PloopyException If an error occurs while executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException;

    /**
     * Returns whether this command is exit command.
     */
    public abstract boolean isExit();
}

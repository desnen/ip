package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;



/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Adds given task to TaskList, and prints the confirmation message upon successful addition.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for persistence operations.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException {
        tasks.addTask(task);
        return ui.showAddTask(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

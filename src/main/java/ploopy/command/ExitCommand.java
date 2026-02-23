package ploopy.command;

import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.TaskList;

/**
 * Exits the application.
 * Saves tasks to storage and displays a goodbye message to the user.
 */
public class ExitCommand extends Command {

    public ExitCommand() {}

    /**
     * Saves tasks in TaskList to file and displays exit message.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

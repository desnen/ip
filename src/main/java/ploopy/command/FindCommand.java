package ploopy.command;

import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.TaskList;

/**
 * Finds all tasks with matching keyword in description from the task list.
 */
public class FindCommand extends Command {
    private final String keyword;
    /**
     * Constructor for FindCommand.
     * @param keyword the keyword that to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        assert keyword != null;
    }

    /**
     * Finds all tasks with matching keyword in description from the task list.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI used to display messages.
     * @param storage The storage used for persistence operations.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.find(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package ploopy.command;

import ploopy.task.Task;
import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;

public class AddCommand extends Command{

    public AddCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

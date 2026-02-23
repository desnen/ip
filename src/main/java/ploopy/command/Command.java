package ploopy.command;

import ploopy.PloopyException;
import ploopy.Storage;
import ploopy.Ui;
import ploopy.task.Task;
import ploopy.task.TaskList;

public abstract class Command {
    protected Task task;

    public Command() {}

    public Command(Task task) {
        this.task = task;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException;
    public abstract boolean isExit();
}

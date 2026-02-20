package ploopy.command;

import ploopy.task.Task;
import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;
import ploopy.PloopyException;

public abstract class Command {

    public Task task;

    public final static String LINE = "______________________________________";

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PloopyException;

    public abstract boolean isExit();

    public Command(){};

    public Command(Task task) {
        this.task = task;
    }
}

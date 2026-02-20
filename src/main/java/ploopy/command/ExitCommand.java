package ploopy.command;

import ploopy.task.TaskList;
import ploopy.Ui;
import ploopy.Storage;

public class ExitCommand extends Command{

    public ExitCommand(){}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    };
}

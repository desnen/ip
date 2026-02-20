public class DeleteCommand extends Command{

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.give(index);
        ui.showDelete(index, t, tasks.getSize());
        tasks.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
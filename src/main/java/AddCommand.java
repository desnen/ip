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

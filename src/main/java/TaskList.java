import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> tasks;

    public final static String LINE = "______________________________________";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void list() {
        String op = LINE + "\n" + "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            op += "\n" + (i + 1) + "." + tasks.get(i);
        }
        System.out.println(op);
    }

    public Task give(int i) {
        if (i < tasks.size()) {
            return tasks.get(i);
        } else {
            return null;
        }
    }

    public boolean isEnd(int i) {
        return i >= tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public int getSize() {
        return tasks.size();
    }

    public void mark(boolean isMark, int index) throws PloopyException {
        try {
            tasks.get(index).mark(isMark);
        } catch (IndexOutOfBoundsException e) {
            throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void delete(int index) {
        tasks.remove(index);
    }
}

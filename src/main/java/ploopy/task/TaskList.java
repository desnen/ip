package ploopy.task;

import java.util.ArrayList;
import java.util.List;

import ploopy.PloopyException;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void list() {
        StringBuilder op = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            op.append("\n").append(i + 1).append(".").append(tasks.get(i));
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
            throw new PloopyException("OOPS!!! Index is out of bounds!");
        }
    }

    public void delete(int index) throws PloopyException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new PloopyException("OOPS!!! Index is out of bounds!");
        }
    }
}

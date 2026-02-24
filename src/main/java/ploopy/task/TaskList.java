package ploopy.task;

import java.util.ArrayList;
import java.util.List;

import ploopy.PloopyException;

/**
 * Represents a collection of tasks managed by the application.
 * Provides operations for listing, adding, marking, and deleting tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string with all tasks currently stored in the list.
     */
    public String list() {
        StringBuilder op = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            op.append("\n").append(i + 1).append(".").append(tasks.get(i));
        }
        return op.toString();
    }

    /**
     * Returns the task at the index.
     *
     * @param i The index of the task.
     * @return The task at the specified index, or null if out of range.
     */
    public Task give(int i) {
        if (i < tasks.size()) {
            return tasks.get(i);
        } else {
            return null;
        }
    }

    /**
     * Returns whether the given index is the end of the task list.
     *
     * @param i The index to check.
     * @return True if the index is beyond the last task.
     */
    public boolean isEnd(int i) {
        return i >= tasks.size();
    }

    /**
     * Adds the specified task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) throws PloopyException {
        for (Task k : tasks) {
            if (k.equals(t)) {
                throw new PloopyException("Identical task already exists");
            }
        }
        tasks.add(t);
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return The task list size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Updates the completion status of the task at the specified index.
     *
     * @param isMark True to mark the task as done, false to unmark it.
     * @param index The zero-based index of the task.
     * @throws PloopyException If the index is invalid.
     */
    public void mark(boolean isMark, int index) throws PloopyException {
        try {
            tasks.get(index).mark(isMark);
        } catch (IndexOutOfBoundsException e) {
            throw new PloopyException("OOPS!!! Index is out of bounds!");
        }
    }
    /**
     * Returns a string with all tasks with matching keyword in their description.
     */
    public String find(String keyword) {
        StringBuilder op = new StringBuilder("Here are the matching tasks in your list:");
        Task curr;
        int count = 1;
        for (Task task : tasks) {
            curr = task;
            if (curr.description.contains(keyword)) {
                op.append("\n").append(count).append(".").append(curr);
                count += 1;
            }
        }
        return op.toString();
    }

    /**
     * Removes the task at the specified one-based index.
     *
     * @param index The one-based index of the task to remove.
     * @throws PloopyException If the index is invalid.
     */
    public void delete(int index) throws PloopyException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new PloopyException("OOPS!!! Index is out of bounds!");
        }
    }
}

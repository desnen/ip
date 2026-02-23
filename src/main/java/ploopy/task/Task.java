package ploopy.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task.
     *
     * @param x True to mark the task as done, false otherwise.
     */
    public void mark(boolean x) {
        isDone = x;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    /**
     * Returns the encoded representation of the task for storage.
     *
     * @return The encoded task string.
     */
    public String encodeString() {
        return "";
    }
}

package ploopy.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String encodeString() {
        if (isDone) {
            return "T/1/" + description;
        } else {
            return "T/0/" + description;
        }
    }
}

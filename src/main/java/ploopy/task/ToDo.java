package ploopy.task;

import java.util.Objects;

/**
 * Represents a todo task.
 */
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof ToDo t) {
            return description.equals(t.description);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}

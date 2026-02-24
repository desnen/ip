package ploopy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import ploopy.PloopyException;

/**
 * Represents a task that must be completed before a specific date.
 */
public class Deadline extends Task {

    protected LocalDate time;

    /**
     * Constructs a Deadline task with the specified description and date.
     *
     * @param description The description of the deadline task.
     * @param time The deadline date in ISO-8601 format.
     * @throws PloopyException If the date format is invalid.
     */
    public Deadline(String description, String time) throws PloopyException {
        super(description);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: "
                        + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String encodeString() {
        if (isDone) {
            return "D/1/" + description + "/" + time;
        } else {
            return "D/0/" + description + "/" + time;
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
        if (obj instanceof Deadline d) {
            return description.equals(d.description) && time.equals(d.time);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, time);
    }
}

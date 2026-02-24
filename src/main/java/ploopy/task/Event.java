package ploopy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import ploopy.PloopyException;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an Event task with the specified description and date range.
     *
     * @param description The description of the event task.
     * @param start The start date in ISO-8601 format.
     * @param end The end date in ISO-8601 format.
     * @throws PloopyException If either date format is invalid.
     */
    public Event(String description, String start, String end) throws PloopyException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                        + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                        + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String encodeString() {
        if (isDone) {
            return "E/1/" + description + "/" + start + "/" + end;
        } else {
            return "E/0/" + description + "/" + start + "/" + end;
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
        if (obj instanceof Event e) {
            return description.equals(e.description) && start.equals(e.start)
                    && end.equals((e.end));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, start, end);
    }
}

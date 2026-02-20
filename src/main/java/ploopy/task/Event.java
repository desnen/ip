package ploopy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import ploopy.PloopyException;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

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
}

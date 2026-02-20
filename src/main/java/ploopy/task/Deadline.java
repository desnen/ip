package ploopy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ploopy.PloopyException;

public class Deadline extends Task {

    protected LocalDate time;

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
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
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

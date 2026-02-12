import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
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

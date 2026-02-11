public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + start + " to: " + end + ")";
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

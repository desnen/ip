public class Deadline extends Task {

    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + time + ")";
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

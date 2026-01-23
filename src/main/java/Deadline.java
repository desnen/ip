public class Deadline extends Task {

    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String msg() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + time + ")";
    }
}

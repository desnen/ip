public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String msg() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}

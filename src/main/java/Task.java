public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Task mark(boolean toMark) {
        if (toMark == isDone) {
            System.out.println(description + " is already " + (toMark ? "un" : "") + "marked.");
        } else {
            isDone = toMark;
            System.out.println(description + " has been updated:\n" + this);
        }
        return this;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

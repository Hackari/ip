public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        System.out.println("Created " + this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

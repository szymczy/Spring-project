package pl.ue.project.data;

public enum CopyStatus {
    AVAILABLE("AVAILABLE"),
    PREPARING("PREPARING"),
    READY_TO_PICK_UP("READY_TO_PICK_UP"),
    BORROWED("BORROWED"),
    OVERDUE("OVERDUE");

    private final String text;

    CopyStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

package FYPMS.student;

/**
 * Represents the status of studnent
 */
public enum StudentStatus {
    NO_PROJECT("NO PROJECT"),
    REQUESTED_PROJECT("REQUESTED PROJECT"),
    REQUESTED_CHANGE("REQUESTED CHANGE"),
    ASSIGNED_PROJECT("ASSIGNED PROJECT"),
    REQUESTED_DEREGISTER("REQUESTED DEREGISTER"),
    DEREGISTERED_PROJECT("DEREGISTERED PROJECT");

    private final String toString;

    StudentStatus(String toString) {
        this.toString = toString;
    }

    public static StudentStatus intToEnum(int num) {
        return switch (num) {
            case 1 -> ASSIGNED_PROJECT;
            case 2 -> REQUESTED_PROJECT;
            case 3 -> REQUESTED_CHANGE;
            case 4 -> REQUESTED_DEREGISTER;
            case 5 -> DEREGISTERED_PROJECT;
            default -> NO_PROJECT;
        };

    }
}
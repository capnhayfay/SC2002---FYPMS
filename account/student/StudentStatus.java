package account.student;

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

    public static StudentStatus StudentStatusToEnum(String status) {
        return switch (status) {
            case "ASSIGNED_PROJECT" -> ASSIGNED_PROJECT;
            case "REQUESTED_PROJECT" -> REQUESTED_PROJECT;
            case "REQUESTED_CHANGE" -> REQUESTED_CHANGE;
            case "REQUESTED_DEREGISTER" -> REQUESTED_DEREGISTER;
            case "DEREGISTERED_PROJECT" -> DEREGISTERED_PROJECT;
            default -> NO_PROJECT;
        };

    }
}
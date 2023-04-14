package account.student;

/**
 * Represents the status of studnent
 */
public enum StudentStatus {
    NO_PROJECT("NO PROJECT"),
    REQUESTED_PROJECT("REQUESTED PROJECT"),
    ASSIGNED_PROJECT("ASSIGNED PROJECT"),
    DEREGISTERED_PROJECT("DEREGISTERED PROJECT");

    private final String toString;

    StudentStatus(String toString) {
        this.toString = toString;
    }

    public static StudentStatus StudentStatusToEnum(String status) {
        return switch (status) {
            case "ASSIGNED_PROJECT" -> ASSIGNED_PROJECT;
            case "REQUESTED_PROJECT" -> REQUESTED_PROJECT;
            case "DEREGISTERED_PROJECT" -> DEREGISTERED_PROJECT;
            default -> NO_PROJECT;
        };

    }
}
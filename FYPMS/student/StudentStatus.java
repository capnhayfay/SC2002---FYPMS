package FYPMS.student;

/**
 * Represents the status of studnent
 */
public enum StudentStatus {
    NO_PROJECT("NO PROJECT"),
    REQUESTED_PROJECT("REQUESTED PROJECT"),
    REQUESTED_CHANGE("REQUESTED CHANGE"),
    ASSIGNED_PROJECT("ASSIGNED PROJECT");

    private final String toString;
    StudentStatus(String toString) {
        this.toString = toString;
    }

    public static StudentStatus intToEnum(int num){
        return switch (num) {
            case 1 -> NO_PROJECT;
            case 2 -> REQUESTED_PROJECT;
            case 3 -> REQUESTED_CHANGE;
            default -> ASSIGNED_PROJECT;
        };

    }
}
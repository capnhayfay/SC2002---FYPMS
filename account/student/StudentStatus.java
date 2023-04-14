package account.student;

/**
 * The {@code StudentStatus} enumeration represents the status of a student's
 * project request. Possible values are {@code NO_PROJECT},
 * {@code REQUESTED_PROJECT}, {@code ASSIGNED_PROJECT}, and
 * {@code DEREGISTERED_PROJECT}.
 */
public enum StudentStatus {
    /**
     * Indicates that the student has not requested any projects yet.
     */
    NO_PROJECT("NO PROJECT"),
    /**
     * Indicates that the student has requested a project but has not been
     * assigned one yet.
     */
    REQUESTED_PROJECT("REQUESTED PROJECT"),
    /**
     * Indicates that the student has been assigned a project.
     */
    ASSIGNED_PROJECT("ASSIGNED PROJECT"),
    /**
     * Indicates that the student has deregistered from a previously assigned
     * project.
     */
    DEREGISTERED_PROJECT("DEREGISTERED PROJECT");

    /**
     * The string representation of the enumeration value.
     */
    private final String toString;

    /**
     * Constructs a new {@code StudentStatus} enumeration with the specified
     * string representation.
     *
     * @param toString the string representation of the enumeration value
     */
    StudentStatus(String toString) {
        this.toString = toString;
    }

    /**
     * Returns the {@code StudentStatus} enumeration value corresponding to the
     * specified string representation.
     *
     * @param status the string representation of the enumeration value
     * @return the {@code StudentStatus} enumeration value corresponding to the
     *         specified string representation
     */
    public static StudentStatus StudentStatusToEnum(String status) {
        return switch (status) {
            case "ASSIGNED_PROJECT" -> ASSIGNED_PROJECT;
            case "REQUESTED_PROJECT" -> REQUESTED_PROJECT;
            case "DEREGISTERED_PROJECT" -> DEREGISTERED_PROJECT;
            default -> NO_PROJECT;
        };
    }
}

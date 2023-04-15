package src.FYPMS.project;

/**
 * Represents the status of the FYP
 */
public enum FYPStatus {
    /**
     * Project available
     */
    AVAILABLE("AVAILABLE"),
    /**
     * Project reserved
     */
    RESERVED("RESERVED"),
    /**
     * Project unavailable
     */
    UNAVAILABLE("UNAVAILABLE"),
    /**
     * Project Allocated
     */
    ALLOCATED("ALLOCATED");

    /**
     * @param toString String form of Enum FYPStatus
     */
    FYPStatus(String toString) {
    }

    /**
     * Converts string representation to FYPStatus enum
     *
     * @param status String form of status
     * @return Enum version of Status: FYPStatus
     */
    public static FYPStatus StringtoStatus(String status) {
        return switch (status) {
            case "RESERVED" -> RESERVED;
            case "UNAVAILABLE" -> UNAVAILABLE;
            case "ALLOCATED" -> ALLOCATED;
            default -> AVAILABLE;
        };

    }
}

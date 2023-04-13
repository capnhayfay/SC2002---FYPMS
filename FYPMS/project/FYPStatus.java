package FYPMS.project;

/**
 * Represents the status of the FYP
 */
public enum FYPStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED"),
    UNAVAILABLE("UNAVAILABLE"),
    ALLOCATED("ALLOCATED");

    private final String toString;

    FYPStatus(String toString) {
        this.toString = toString;
    }

    public static FYPStatus StringtoStatus(String status) {
        return switch (status) {
            case "RESERVED" -> RESERVED;
            case "UNAVAILABLE" -> UNAVAILABLE;
            case "ALLOCATED" -> ALLOCATED;
            default -> AVAILABLE;
        };

    }
}
